/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import projekat.exceptions.InvalidDriverInputException;
import projekat.io.RoundIO;
import projekat.util.DriverUtil;

/**
 *
 * @author Milica
 */
public class Qualification implements Driven {

    private QualificationType type;
    LocalDate qualiDate;
    private LinkedHashMap<Driver, Integer> results = new LinkedHashMap<>();
    private LinkedHashMap<Driver, Double> awardedPoints = new LinkedHashMap<>();
    private LinkedHashMap<Team, Double> awardedTeamPoints = new LinkedHashMap<>();
    
    public Qualification() {
    }
    
    public Qualification(QualificationType type, LocalDate qualiDate) {
        this.type = type;
        this.qualiDate = qualiDate;
    }
    
    public Qualification(Qualification q) {
        this.type = q.type;
        this.qualiDate = q.qualiDate;
    }
    
    public QualificationType getType() {
        return type;
    }
    
    public void setType(QualificationType type) {
        this.type = type;
    }
    
    public LocalDate getQualiDate() {
        return qualiDate;
    }
    
    public void setQualiDate(LocalDate qualiDate) {
        this.qualiDate = qualiDate;
    }
    
    public LinkedHashMap<Driver, Integer> getResults() {
        return results;
    }
    
    public void setResults(LinkedHashMap<Driver, Integer> results) {
        this.results = results;
    }
    
    public LinkedHashMap<Driver, Double> getAwardedPoints() {
        return awardedPoints;        
    }
    
    public void setAwardedPoints(LinkedHashMap<Driver, Double> awardedPoints) {
        this.awardedPoints = awardedPoints;
    }
    
    public LinkedHashMap<Team, Double> getAwardedTeamPoints() {
        return awardedTeamPoints;
    }
    
    public void setAwardedTeamPoints(LinkedHashMap<Team, Double> awardedTeamPoints) {
        this.awardedTeamPoints = awardedTeamPoints;
    }
    
    @Override
    public String toString() {
        return "Qualification " + "type: " + type + ", qualiDate " + qualiDate + "\n Quali results \n" + results + "\n Quali awardedPoints \n" + awardedPoints;
    }
    
    /**
     * Omogućava korisniku da unese rezultate 
     * @param driverList - lista svih vozača
     */
    @Override
    public void addResults(List<Driver> driverList) {
        List<Integer> carNum = new ArrayList<>();
        carNum.add(0);
        for (int position = 1; position <= 20; position++) {
            boolean bool = true;
            while(bool){
                try{
                System.out.println("For position #" + position + " add Driver");
                Driver key = DriverUtil.searchByCarNumber(driverList);
                int tmp = 0;
                    for (Integer integer : carNum) {
                        if (integer == key.getCarNumber()) {
                            tmp+=1;
                        }
                    }
                    carNum.add(key.getCarNumber());
                    if(tmp > 0){
                        throw new InvalidDriverInputException("Chosen driver is already entered.");
                    } else {
                     this.results.put(key, position);
                     bool = false;
                    }
                } catch (InvalidDriverInputException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    /**
     * Obračunava poene na osnou rezultata. Ukoliko su sprint kvalifikacije poene dobijaju samo prva tri vozača. 
     * @param results - lista rezultata za koje se obračunavaju poeni
     */
    @Override
    public void calculateDriverPoints(LinkedHashMap<Driver, Integer> results) {
        if (this.type.equals(QualificationType.SPRINT)) {
            for (Driver d : results.keySet()) {
                switch (results.get(d)) {
                    case 1:
                        this.awardedPoints.put(d, (double) 3);
                        break;
                    case 2:
                        this.awardedPoints.put(d, (double) 2);
                        break;
                    case 3:
                        this.awardedPoints.put(d, (double) 1);
                        break;
                    default:
                        this.awardedPoints.put(d, (double) 0);
                        break;
                }
            }
        } else {
            for (Driver d : results.keySet()) {
                this.awardedPoints.put(d, (double) 0);
            }
        }
    }
    
    /**
     * Obračunava poene timova na osnovu poena vozača
     * @param points - poeni vozača
     * @param teamList - lista svih timova
     */
    @Override
    public void calculateTeamPoints(LinkedHashMap<Driver, Double> points, List<Team> teamList) {
        for (Team team : teamList) {
            double result = 0;
            for (Driver d : points.keySet()) {
                if (d.getTeamCode().toLowerCase().equals(team.getTeamCode().toLowerCase())) {
                    result += points.get(d);
                }
            }
            this.awardedTeamPoints.put(team, result);
        }        
    }
    
    /**
     * Štampa poene vozača
     */
    @Override
    public void printPoints() {
        System.out.println(this.type + " QUALIFICATION DRIVERS RESULTS" + this.qualiDate.toString());
        RoundIO.printDriversPoints(awardedPoints);
    }
    
    /**
     * Štampa poene vozača u fajl
     */
    @Override
    public void printPointsToFile() {
        String filename = this.type + "_QUALIFICATION_DRIVERS" + this.qualiDate.toString() + ".txt";
        RoundIO.printDriversPointsToFile(awardedPoints, filename);
    }
    
    /**
     * Štampa poene timova
     */
    @Override
    public void printTeamResult() {
        System.out.println(this.type + " QUALIFICATION TEAM RESULTS" + this.qualiDate.toString());
        RoundIO.printTeamsPoints(awardedTeamPoints);
    }
    
    /**
     * Štampa poene timova u fajl
     */
    @Override
    public void printTeamResultToFile() {
        String filename = this.type + "_QUALIFICATION_TEAMS" + this.qualiDate.toString() + ".txt";
        RoundIO.printTeamsPointsToFile(awardedTeamPoints, filename);
    }
    
}
