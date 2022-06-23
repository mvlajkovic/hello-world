/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import projekat.data.Team;

/**
 *
 * @author Milica
 */
public class TeamIO {

    public final static String TEAMS = "teams.txt";

    /**
     * Metoda učitava timove iz fajla teams.txt. Kreira nove instance klase
     * Teams i dodaje ih u listu svih timova.
     *
     * @return - lista timova
     */
    public static List<Team> loadTeams() {
        BufferedReader reader = null;
        List<Team> teamList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(TEAMS));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("/");
                //str[0] - name; str[1] - team code; str[2] - location; 
                Team team = new Team(str[0], str[1], str[2]);
                teamList.add(team);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return teamList;
    }

    /**
     * Metoda štampa pojedinačni tim u fajl
     *
     * @param team - tim koji se štampa
     * @param filename - fajl u koji se štampa
     */
    public static void printTeamToFile(Team team, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(team.toString());
            writer.newLine();
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
     * Metoda štampa sve timove pojedinačno sa listom vozača
     *
     * @param teamList - lista svih timova
     */
    public static void printAllTeams(List<Team> teamList) {
        for (Team team : teamList) {
            System.out.println(team);
            System.out.println(team.getDriverList());
        }
    }

}
