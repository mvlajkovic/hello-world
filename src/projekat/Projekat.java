/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekat;

import java.util.ArrayList;
import java.util.List;
import projekat.data.ConstructorChampionship;
import projekat.data.Driver;
import projekat.data.DriverChampionship;
import projekat.data.Team;
import projekat.data.Track;
import projekat.io.DriverIO;
import projekat.io.TeamIO;
import projekat.io.TrackIO;
import projekat.util.ChampionshipUtil;
import projekat.util.TeamUtil;

/**
 *
 * @author Milica
 */
public class Projekat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Driver> driverList = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();
        List<Track> trackList = new ArrayList<>();
        DriverChampionship wdc = new DriverChampionship();
        ConstructorChampionship wcc = new ConstructorChampionship();
        System.out.println("STARTING NEW SEASON...");
        driverList = DriverIO.loadDrivers();
        teamList = TeamIO.loadTeams();
        trackList = TrackIO.loadTracks();
        TeamUtil.addDriversToTeam(driverList, teamList);
        ChampionshipUtil.initializeChampionships(wdc, wcc, ChampionshipUtil.inputSeason());
        System.out.println("Season successfully started.");
        Menu.mainMenu(driverList, teamList, trackList, wdc, wcc);
        
    }

}
