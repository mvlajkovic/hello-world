/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat;

import java.util.List;
import java.util.Scanner;
import projekat.data.ConstructorChampionship;
import projekat.data.Driver;
import projekat.data.DriverChampionship;
import projekat.data.Round;
import projekat.data.Team;
import projekat.data.Track;
import projekat.exceptions.InvalidMenuOptionException;
import projekat.io.DriverIO;
import projekat.io.RoundIO;
import projekat.io.TeamIO;
import projekat.io.TrackIO;
import projekat.util.DriverUtil;
import projekat.util.RoundUtil;

/**
 *
 * @author Milica
 */
public class Menu {

    /**
     * Metoda pokreće glavni meni i omogućuje korisniku da bira podmeni
     *
     * @param driverList - lista svih vozača
     * @param teamList - lista svih timova
     * @param trackList - lista svih staza
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     */
    public static void mainMenu(List<Driver> driverList, List<Team> teamList, List<Track> trackList, DriverChampionship wdc, ConstructorChampionship wcc) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - Drivers submenu \n 2 - Teams submenu \n 3 - Tracks submenu \n 4 - Rounds submenu \n 5 - Championship submenu \n 0 - Exit");
                System.out.println("Choose submenu");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        driverSubmenu(driverList, teamList);
                        break;
                    case 2:
                        teamSubmenu(teamList);
                        break;
                    case 3:
                        trackSubmenu(trackList);
                        break;
                    case 4:
                        roundsSubmenu(driverList, teamList, trackList, wdc, wcc);
                        break;
                    case 5:
                        championshipSubmenu(driverList, teamList, wdc, wcc);
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

    /**
     * Metoda pokreće "Driver" podmeni, omogućuje korisniku da lista vozače,
     * definiše uloge vozača, dodaje nove vozače i pretražuje pojedinačnog
     * vozača
     *
     * @param driverList lista svih vozača
     * @param teamList lista svih timova
     */
    public static void driverSubmenu(List<Driver> driverList, List<Team> teamList) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - List of Drivers \n 2 - Define All Driver Roles \n 3 - Add new Driver \n 4 - Search Driver \n 0 - Exit");
                System.out.println("Choose an option");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        DriverIO.printAllDrivers(driverList);
                        break;
                    case 2:
                        DriverUtil.defineDriverRole(driverList);
                        break;
                    case 3:
                        DriverUtil.addNewDriver(driverList, teamList);
                        break;
                    case 4:
                        System.out.println(DriverUtil.searchByCarNumber(driverList));
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

    /**
     * Metoda pokreće "Team" podmeni, omogućuje korisniku da lista sve timove
     *
     * @param teamList lista svih timova
     */
    public static void teamSubmenu(List<Team> teamList) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - List of Teams \n 0 - Exit");
                System.out.println("Choose an option");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        TeamIO.printAllTeams(teamList);
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

    /**
     * Metoda pokreće "Track" podmeni, omogućuje korisniku da lista sve staze
     *
     * @param trackList lista svih staza
     */
    public static void trackSubmenu(List<Track> trackList) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - List of Tracks \n 0 - Exit");
                System.out.println("Choose an option");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        TrackIO.printAllTracks(trackList);
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

    /**
     * Metoda pokreće "Rounds" podmeni, omogućuje korisniku da prelista sve
     * runde, doda novu rundu, prikaže pojedinačnu rundu, doda rezultate za
     * pojedinačnu runud i lista rezultate za pojedinačnu rundu
     *
     * @param driverList - lista svih vozača
     * @param teamList - lista svih timova
     * @param trackList - lista svih staza
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     */
    public static void roundsSubmenu(List<Driver> driverList, List<Team> teamList, List<Track> trackList, DriverChampionship wdc, ConstructorChampionship wcc) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - List of Rounds \n 2 - Add New Round \n 3 - Show individual round \n 4 - Add Results For Round \n 5 - List results for Round \n 0 - Exit");
                System.out.println("Choose an option");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        RoundIO.printChampionshipRounds(wdc.getChampionshipRounds());
                        break;
                    case 2:
                        RoundUtil.createAndAddNewRound(wdc, wcc, trackList);
                        break;
                    case 3:
                        Round r = RoundUtil.chooseIndividualRound(wdc.getChampionshipRounds());
                        if (r == null) {
                            System.out.println("No rounds yet.");
                        } else {
                            System.out.println(r.toString() + "\n QUALIFICATION \n" + r.getQualification().toString() + "\n RACE \n" + r.getRace().toString());
                        }
                        break;
                    case 4:
                        Round round = RoundUtil.chooseIndividualRound(wdc.getChampionshipRounds());
                        if (round == null) {
                            System.out.println("No rounds yet.");
                        } else {
                            RoundUtil.inputResultsForIndividualRound(round, driverList, teamList, wdc, wcc);
                        }
                        break;
                    case 5:
                        Round ro = RoundUtil.chooseIndividualRound(wdc.getChampionshipRounds());
                        if (ro == null) {
                            System.out.println("No rounds yet.");
                        } else {
                            ro.getQualification().printPoints();
                            ro.getQualification().printTeamResult();
                            ro.getRace().printPoints();
                            ro.getRace().printTeamResult();
                        }
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

    /**
     * Metoda pokreće "Championship" podmeni, omogućuje korisniku da ažurira
     * stanje u šampionatima (vozača i konstruktora), lista stanje u šampionatu
     * vozača i timova (posebno), proglasi šampione (vozača i konstruktora) i
     * prikažu šampione.
     *
     * @param driverList - lista svih vozača
     * @param teamList - lista svih timova
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     */
    public static void championshipSubmenu(List<Driver> driverList, List<Team> teamList, DriverChampionship wdc, ConstructorChampionship wcc) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - Update Standings \n 2 - Driver Championhip Standings  \n 3 - Constructor Championhip Standings  \n 4 - Declare Champions \n 5 - Print Champions \n 0 - Exit");
                System.out.println("Choose an option");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidMenuOptionException("You have to choose a valid option");
                }
                int i = Integer.parseInt(str);
                switch (i) {
                    case 0:
                        bool = false;
                        break;
                    case 1:
                        if (wdc.getChampionshipRounds().isEmpty() || wcc.getChampionshipRounds().isEmpty()) {
                            System.out.println("Nothing to  update yet.");
                        } else {
                            wdc.updateStandings(driverList);
                            wcc.updateStandings(teamList);
                            wdc.printStandingsToFile();
                            wcc.printStandingsToFile();
                            System.out.println("Successfully updated.");
                        }
                        break;
                    case 2:
                        if (wdc.getStandings().isEmpty()) {
                            System.out.println("Nothing to show yet.");
                        } else {
                            wdc.readStandings();
                        }
                        break;
                    case 3:
                        if (wcc.getStandings().isEmpty()) {
                            System.out.println("Nothing to show yet.");
                        } else {
                            wcc.readStandings();
                        }
                        break;
                    case 4:
                        if (wdc.getStandings().isEmpty() || wcc.getStandings().isEmpty()) {
                            System.out.println("No results yet.");
                        } else {
                            wdc.declareChampion();
                            wdc.printChampionToFile();
                            wcc.declareChampion();
                            wcc.printChampionToFile();
                        }
                        break;
                    case 5:
                        if (wdc.getStandings().isEmpty() || wcc.getStandings().isEmpty()) {
                            System.out.println("No results yet.");
                        } else {
                            wdc.readChampion();
                            wcc.readChampion();
                        }
                        break;
                    default:
                        throw new InvalidMenuOptionException("You have to choose a valid option");
                }
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to choose a valid option");
            }
        }
    }

}
