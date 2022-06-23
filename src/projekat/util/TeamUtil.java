/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.util.List;
import projekat.data.Driver;
import projekat.data.Team;

/**
 *
 * @author Milica
 */
public class TeamUtil {

    /**
     * Dodaje sve vozače sa liste timovima (na osnovu teamCode)
     *
     * @param driverList - lista svih vozača
     * @param teamList - lista svih timova
     */
    public static void addDriversToTeam(List<Driver> driverList, List<Team> teamList) {
        //go through each team and get teamcode and then go throug each driver and assign driver to the team.
        for (Driver driver : driverList) {
            addIndividualDriverToTeam(driver, teamList);
        }
    }

    /**
     * Dodaje pojedinačnog vozača timu (na osnovu teamCode)
     *
     * @param driver - vozač kog dodajemo timu
     * @param teamList - lista svih timova
     */
    public static void addIndividualDriverToTeam(Driver driver, List<Team> teamList) {
        for (Team team : teamList) {
            if (team.getTeamCode().toUpperCase().equals(driver.getTeamCode().toUpperCase())) {
                team.addDriverToList(driver);
            }
        }
    }

}
