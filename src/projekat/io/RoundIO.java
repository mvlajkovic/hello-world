/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import projekat.data.Driver;
import projekat.data.Round;
import projekat.data.Team;
import projekat.util.GeneralUtil;

/**
 *
 * @author Milica
 */
public class RoundIO {

    /**
     * Metoda štampa poene vozača u fajl - sortirano po vrednosti od najveće ka najmanjoj
     * @param points LinkedHashMap koja sadrži poene za svakog vozača
     * @param filename - ime fajla u koji se štampa
     */
    public static void printDriversPointsToFile(LinkedHashMap<Driver, Double> points, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            LinkedHashMap<Driver, Double> tmp = GeneralUtil.sortByDriverPoints(points);
            int position = 1;
            for (Driver driver : tmp.keySet()) {
                String str = position + ". " + driver.toString() + " - " + tmp.get(driver);
                position++;
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Metoda štampa poene vozača - sortirano po vrednosti od najveće ka najmanjoj.
     * @param points LinkedHashMap koja sadrži poene za svakog vozača
     */
    public static void printDriversPoints(LinkedHashMap<Driver, Double> points) {
        LinkedHashMap<Driver, Double> tmp = GeneralUtil.sortByDriverPoints(points);
        int position = 1;
        for (Driver driver : tmp.keySet()) {
            String str = position + ". " + driver.toString() + " - " + tmp.get(driver);
            System.out.println(str);
            position++;
        }

    }

    /**
     * Metoda štampa poene timova u fajl - sortirano po vrednosti od najveće ka najmanjoj
     * @param points LinkedHashMap koja sadrži poene za svaki tim
     * @param filename fajl u koji se štampa
     */
    public static void printTeamsPointsToFile(LinkedHashMap<Team, Double> points, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            LinkedHashMap<Team, Double> tmp = GeneralUtil.sortByTeamPoints(points);
            int position = 1;
            for (Team team : tmp.keySet()) {
                String str = position + ". " + team.toString() + " - " + tmp.get(team);
                position++;
                writer.write(str);
                writer.newLine();
            }  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Metoda štampa poene timova - sortirano po vrednosti od najveće ka najmanjoj
     * @param points LinkedHashMap koja sadrži poene za svaki tim
     */
    public static void printTeamsPoints(LinkedHashMap<Team, Double> points) {
        LinkedHashMap<Team, Double> tmp = GeneralUtil.sortByTeamPoints(points);
        int position = 1;
        for (Team team : tmp.keySet()) {
            String str = position + ". " + team.toString() + " - " + tmp.get(team);
            System.out.println(str);
            position++;
        }
    }
    
    /**
     * Metoda štampa svaku pojedinačnu rundu u šampionatu
     * @param championshipRounds 
     */
    public static void printChampionshipRounds(List<Round> championshipRounds){
       
        for (Round round : championshipRounds) {
            System.out.println((championshipRounds.indexOf(round)+1)+ ". " + round.toString());
            
        }
    
    }

}
