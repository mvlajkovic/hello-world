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
import projekat.util.RoundUtil;

/**
 *
 * @author Milica
 */
public class Race implements Driven {

    private int completedLaps;
    private int raceLaps;
    LocalDate raceDate;
    private LinkedHashMap<Driver, Integer> results = new LinkedHashMap<>();
    private LinkedHashMap<Driver, Double> awardedPoints = new LinkedHashMap<>();
    private LinkedHashMap<Team, Double> awardedTeamPoints = new LinkedHashMap<>();

    public Race() {
    }

    public Race(int raceLaps, int completedLaps, LocalDate raceDate) {
        this.raceLaps = raceLaps;
        this.completedLaps = completedLaps;
        this.raceDate = raceDate;
    }

    public Race(Race r) {
        this.raceLaps = r.raceLaps;
        this.completedLaps = r.completedLaps;
        this.raceDate = r.raceDate;
    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDate date) {
        this.raceDate = date;
    }

    public int getCompletedLaps() {
        return completedLaps;
    }

    public void setCompletedLaps(int completedLaps) {
        this.completedLaps = completedLaps;
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

    public int getRaceLaps() {
        return raceLaps;
    }

    public void setRaceLaps(int raceLaps) {
        this.raceLaps = raceLaps;
    }

    public LinkedHashMap<Team, Double> getAwardedTeamPoints() {
        return awardedTeamPoints;
    }

    public void setAwardedTeamPoints(LinkedHashMap<Team, Double> awardedTeamPoints) {
        this.awardedTeamPoints = awardedTeamPoints;
    }

    @Override
    public String toString() {
        return "Race: " + "completed laps " + completedLaps + " of " + raceLaps + " total race laps, on " + raceDate+ "\n Race results \n" + results + "\n Race awardedPoints \n" + awardedPoints;
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
     * Obračunava poene na osnou rezultata. Poeni zavise od % kompletiranih krugova trke u odnosu na predviđen broj krugova
     * @param results - lista rezultata za koje se obračunavaju poeni
     */
    @Override
    public void calculateDriverPoints(LinkedHashMap<Driver, Integer> results) {
        this.completedLaps = RoundUtil.inputCompletedLaps();
        if (this.completedLaps < (this.raceLaps * 0.7)) {
            for (Driver d : results.keySet()) {
                switch (results.get(d)) {
                    case 1:
                        this.awardedPoints.put(d, (double)12.5);
                        break;
                    case 2:
                        this.awardedPoints.put(d, (double) 9);
                        break;
                    case 3:
                        this.awardedPoints.put(d, (double) 7.5);
                        break;
                    case 4:
                        this.awardedPoints.put(d, (double) 6);
                        break;
                    case 5:
                        this.awardedPoints.put(d, (double) 5);
                        break;
                    case 6:
                        this.awardedPoints.put(d, (double) 4);
                        break;
                    case 7:
                        this.awardedPoints.put(d, (double) 3);
                        break;
                    case 8:
                        this.awardedPoints.put(d, (double) 2);
                        break;
                    case 9:
                        this.awardedPoints.put(d, (double) 1);
                        break;
                    case 10:
                        this.awardedPoints.put(d, (double) 0.5);
                        break;
                    default:
                        this.awardedPoints.put(d, (double) 0);
                        break;
                }
            }
        } else {
            for (Driver d : results.keySet()) {
                switch (results.get(d)) {
                    case 1:
                        this.awardedPoints.put(d, (double)25);
                        break;
                    case 2:
                        this.awardedPoints.put(d, (double) 18);
                        break;
                    case 3:
                        this.awardedPoints.put(d, (double) 15);
                        break;
                    case 4:
                        this.awardedPoints.put(d, (double) 12);
                        break;
                    case 5:
                        this.awardedPoints.put(d, (double) 10);
                        break;
                    case 6:
                        this.awardedPoints.put(d, (double) 8);
                        break;
                    case 7:
                        this.awardedPoints.put(d, (double) 6);
                        break;
                    case 8:
                        this.awardedPoints.put(d, (double) 4);
                        break;
                    case 9:
                        this.awardedPoints.put(d, (double) 2);
                        break;
                    case 10:
                        this.awardedPoints.put(d, (double) 1);
                        break;
                    default:
                        this.awardedPoints.put(d, (double) 0);
                        break;
                }
            }
        }
    }

   /**
    * Obračunava poene timova na osnovu poena vozača
    * @param points - lista poena vozača
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
        System.out.println( "RACE Drivers results" + this.raceDate.toString() + " laps completed: " + this.completedLaps+"/"+this.raceLaps);
        RoundIO.printDriversPoints(awardedPoints);
    }

    /**
     * Štampa poene vozača u fajl
     */
    @Override
    public void printPointsToFile() {
        String filename="RACE_DRIVERS"+this.raceDate.toString()+".txt";
        RoundIO.printDriversPointsToFile(awardedPoints, filename);
    }


    /**
     * Štampa poene timova
     */
    @Override
    public void printTeamResult() {
        System.out.println( "RACE Teams results" + this.raceDate.toString() + " laps completed: " + this.completedLaps+"/"+this.raceLaps);
        RoundIO.printTeamsPoints(awardedTeamPoints);
    }

    /**
     * Štampa poene timova u fajl
     */
    @Override
    public void printTeamResultToFile() {
        String filename="RACE_TEAMS"+this.raceDate.toString()+".txt";
        RoundIO.printTeamsPointsToFile(awardedTeamPoints, filename);
    }

}
