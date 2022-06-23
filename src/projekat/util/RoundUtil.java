/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.util.Scanner;
import projekat.data.QualificationType;
import projekat.data.Round;
import java.time.LocalDate;
import java.util.List;
import projekat.data.ConstructorChampionship;
import projekat.data.Driver;
import projekat.data.DriverChampionship;
import projekat.data.Qualification;
import projekat.data.Race;
import projekat.data.Team;
import projekat.data.Track;
import projekat.exceptions.InsufficientNumberOfLaps;
import projekat.exceptions.InvalidGrandPrixNameException;
import projekat.exceptions.InvalidMenuOptionException;
import projekat.exceptions.InvalidSesionDateInputException;
import projekat.exceptions.NonExistentQualiTypeException;
import projekat.exceptions.NonExistentRoundException;
import projekat.exceptions.NonExistentTrackException;
import projekat.io.RoundIO;

/**
 *
 * @author Milica
 */
public class RoundUtil {

    /**
     * Metoda omogućava korisniku da kreira novu rundu i da je doda aktuelnim
     * šampionatima. Odmah se kreiraju i kvalifikacije i trka.
     *
     * @param wdc - Šampionat vozača
     * @param wcc - Šampionat konstruktora
     * @param trackList - lista staza
     */
    public static void createAndAddNewRound(DriverChampionship wdc, ConstructorChampionship wcc, List<Track> trackList) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        Track raceTrack = null;
        String gpName = "";
        for (Track track : trackList) {
            System.out.println((trackList.indexOf(track) + 1) + " - " + track);
        }
        while (bool) {
            try {
                System.out.println("Input a number of track");
                String str = scan.nextLine();
                if (str == null) {
                    throw new NonExistentTrackException("Non-existent track.");
                }
                int i = Integer.parseInt(str);
                if (i <= 0 && i > trackList.size()) {
                    throw new NonExistentTrackException("Non-existent track.");
                } else if (i > 0 && i <= trackList.size()) {
                    int j = i - 1;
                    raceTrack = trackList.get(j);
                    bool = false;
                }

            } catch (NonExistentTrackException | NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a valid number.");
            }
        }
        bool = true;
        while (bool) {
            try {
                System.out.println("Input a Grand Prix name: ");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidGrandPrixNameException("You have to input a Grand Prix name");
                } else {
                    gpName = str;
                    bool = false;
                }
            } catch (InvalidGrandPrixNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Input quali details");
        Qualification quali = addNewQuali(wdc.getSezona());
        System.out.println("Input race details");
        Race race = addNewRace(wdc.getSezona());
        Round round = new Round(raceTrack, gpName, race, quali);
        ChampionshipUtil.addIndividualRoundToChampionships(wdc, wcc, round);
    }

    /**
     * Omogućava korisniku da definiše datum "sesije" (kvalifikacije/trka) za aktuelnu sezonu
     * @param season - aktuelna sezona
     * @return datum sesije
     */
    public static LocalDate inputSesionDate(int season) {
        LocalDate sesionDate = null;
        String date = null;
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                String tmp1 = null;
                String tmp2 = null;
                System.out.println("Input a number of Month between 1 and 12");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 12");
                }
                int month = Integer.parseInt(str);
                if (month == 0) {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 12");
                } else if (month > 0 && month < 10) {
                    tmp1 = "0" + month + "-";
                } else if (month >= 10 && month <= 12) {
                    tmp1 = month + "-";
                } else {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 12");
                }
                System.out.println("Input a number of day between 1 and 31");
                str = scan.nextLine();
                if (str == null) {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 31");
                }
                int day = Integer.parseInt(str);
                if (day == 0) {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 31");
                } else if (day > 0 && day < 10) {
                    tmp2 = "0" + day;
                } else if (day >= 10 && day <= 31) {
                    if (month == 2 && day >= 29) {
                        throw new InvalidSesionDateInputException("For Month 02 day must be between 1 - 28/29.");
                    } else if (day == 31) {
                        if (month == 4 || month == 6 || month == 9 || month == 11) {
                            throw new InvalidSesionDateInputException("For Month 02 day must be between 1 - 30.");
                        }
                    }
                    tmp2 = "" + day;
                } else {
                    throw new InvalidSesionDateInputException("You have to input a number 01 - 31");
                }
                date = season + "-" + tmp1 + tmp2;
                System.out.println(date);
                sesionDate = LocalDate.parse(date);
                bool = false;
            } catch (InvalidSesionDateInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number.");
            }
        }
        return sesionDate;
    }

    /**
     * Omogućava korisniku da definišu tip kvalifikacija
     * @return - tip kvalifikacija
     */
    public static QualificationType inputQualiType() {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("Input a number of Qualification type:");
                System.out.println(" 1 - " + QualificationType.STANDARD + "\n 2 - " + QualificationType.SPRINT);
                String str = scan.nextLine();
                if (str == null) {

                    throw new NonExistentQualiTypeException("You have to input a number 1 or 2");
                }
                int i = Integer.parseInt(str);
                if (i == 1) {
                    bool = false;
                    return QualificationType.STANDARD;
                }
                if (i == 2) {
                    bool = false;
                    return QualificationType.SPRINT;
                }
                if (i <= 0 || i > 2) {

                    throw new NonExistentQualiTypeException("You have to input a number 1 or 2");
                }
            } catch (NonExistentQualiTypeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number 1 or 2");
            }
        }
        return null;
    }

    /**
     * Omogućava korisniku da dodaju novu trku
     * @param season - sezona za koju se kreira trka
     * @return  kreirana trka
     */
    public static Race addNewRace(int season) {
        Race race = new Race();
        System.out.println("Input SESION DATE: ");
        race.setRaceDate(inputSesionDate(season));
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        int laps = 0;
        while (bool) {
            try {
                System.out.println("Input a number of race laps: ");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InsufficientNumberOfLaps("");
                }
                laps = Integer.parseInt(str);
                if (laps == 0) {
                    throw new InsufficientNumberOfLaps("");
                } else {
                    bool = false;
                }
            } catch (InsufficientNumberOfLaps | NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number greater than 0");
            }
        }
        race.setRaceLaps(laps);
        return race;
    }
    
    /**
     * Omogućava korisniku da dodaju nove kvalifikacije
     * @param season - sezona za koju se kreiraju kvalifikacije
     * @return kreirane kvalifikacije
     */
    public static Qualification addNewQuali(int season) {
        Qualification quali = new Qualification();
        System.out.println("Input SESION DATE: ");
        quali.setQualiDate(inputSesionDate(season));
        quali.setType(inputQualiType());
        return quali;
    }

    /**
     * Omogućava korisniku da unese broj kompletiranik krugova u trci
     * @return broj kompletiranik krugova
     */
    public static int inputCompletedLaps() {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        int laps = 0;
        while (bool) {
            try {
                System.out.println("Input a number of completed race laps: ");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InsufficientNumberOfLaps("");
                }
                laps = Integer.parseInt(str);
                if (laps == 0) {
                    throw new InsufficientNumberOfLaps("");
                } else {
                    bool = false;
                }
            } catch (InsufficientNumberOfLaps | NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number greater than 0");
            }
        }
        return laps;
    }

    /**
     * Omogućava korisniku da sa spiska rundi u šampionatu odabere samo jednu rundu
     * @param championshipRounds - runde u šampionatu
     * @return odabrana runda
     */
    public static Round chooseIndividualRound(List<Round> championshipRounds) {
        RoundIO.printChampionshipRounds(championshipRounds);
        //NonExistentRoundException
        if (championshipRounds.isEmpty()) {
            return null;
        }
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("Input a number of round");
                String str = scan.nextLine();
                if (str == null) {
                    throw new NonExistentRoundException("Non-existent round.");
                }
                int i = Integer.parseInt(str);
                if (i <= 0 && i > championshipRounds.size()) {
                    throw new NonExistentRoundException("Non-existent round.");
                } else if (i > 0 && i <= championshipRounds.size()) {
                    int j = i - 1;
                    bool = false;
                    return championshipRounds.get(j);
                }
            } catch (NonExistentRoundException | NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a valid number.");
            }
        }

        return null;
    }

    /**
     * Omogućava korisniku da za odabranu rundu unese rezultate i obračuna poene
     * @param round - odabrana runda
     * @param driverList - lista vozača
     * @param teamList - lista timova
     * @param wdc - šampionat vozača
     * @param wcc  - šampionat konstruktora
     */
    public static void inputResultsForIndividualRound(Round round, List<Driver> driverList, List<Team> teamList, DriverChampionship wdc, ConstructorChampionship wcc) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("***************************************************************************************************************");
                System.out.println(" 1 - Quali \n 2 - Race \n \n 0 - Exit");
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
                        round.getQualification().addResults(driverList);
                        round.getQualification().calculateDriverPoints(round.getQualification().getResults());
                        round.getQualification().calculateTeamPoints(round.getQualification().getAwardedPoints(), teamList);
                        //wdc.updateStandings(driverList);
                        //wcc.updateStandings(teamList);
                        round.getQualification().printPointsToFile();
                        round.getQualification().printTeamResultToFile();
                        //wdc.printStandingsToFile();
                        //wcc.printStandingsToFile();
                        break;
                    case 2:
                        round.getRace().addResults(driverList);
                        round.getRace().calculateDriverPoints(round.getRace().getResults());
                        round.getRace().calculateTeamPoints(round.getRace().getAwardedPoints(), teamList);
                        // wdc.updateStandings(driverList);
                        // wcc.updateStandings(teamList);
                        round.getRace().printPointsToFile();
                        round.getRace().printTeamResultToFile();
                        // wdc.printStandingsToFile();
                        //wcc.printStandingsToFile();
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
