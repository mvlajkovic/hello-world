/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.time.Year;
import java.util.List;
import java.util.Scanner;
import projekat.data.ConstructorChampionship;
import projekat.data.DriverChampionship;
import projekat.data.Round;
import projekat.exceptions.InvalidSeasonException;

/**
 *
 * @author Milica
 */
public class ChampionshipUtil {

    /**
     * Metoda dodaje sezonu šampionatima konstruktora i vozača
     *
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     * @param season - sezona za koju je kreiran šampionat
     */
    public static void initializeChampionships(DriverChampionship wdc, ConstructorChampionship wcc, int season) {
        wdc.setSezona(season);
        wcc.setSezona(season);
    }

    /**
     * Metoda setuje runde šampionatima na osnovu liste rundi
     *
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     * @param r - lista rudni
     */
    public static void setRoundsOfChampionship(DriverChampionship wdc, ConstructorChampionship wcc, List<Round> r) {
        wdc.setChampionshipRounds(r);
        wcc.setChampionshipRounds(r);
    }

    /**
     * Metoda dodaje pojedinačnu rundu šampionatu
     *
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     * @param r - pojedinačna runda
     */
    public static void addIndividualRoundToChampionships(DriverChampionship wdc, ConstructorChampionship wcc, Round r) {
        wdc.getChampionshipRounds().add(r);
        wcc.getChampionshipRounds().add(r);
    }

    /**
     * Metoda omogućuje korisniku da unese sezonu. Šampionat F1 postoji tek od
     * 1950. godine te nije moguće uneti godinu pre te.
     *
     * @return sezona
     */
    public static int inputSeason() {
        Scanner scan = new Scanner(System.in);
        int season = 1950;
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("Input a season (year between 1950 and present)");
                String str = scan.nextLine();
                season = Integer.parseInt(str);
                int year = Year.now().getValue();
                if ((season <= year) && (season >= 1950)) {
                    bool = false;
                } else {
                    throw new InvalidSeasonException("Year must be between 1950 and present");
                }
            } catch (InvalidSeasonException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Year must be between 1950 and present");
            }
        }
        return season;
    }

}
