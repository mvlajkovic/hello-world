/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import java.util.List;
import java.util.Scanner;
import projekat.data.Driver;
import projekat.data.DriverRole;
import projekat.data.Team;
import projekat.exceptions.CountryCodeException;
import projekat.exceptions.DriverAlreadyExistsException;
import projekat.exceptions.InvalidCarNumberException;
import projekat.exceptions.InvalidTeamCodeException;
import projekat.exceptions.NonExistentCarNumber;
import projekat.exceptions.NonExistentDriverRoleException;
import projekat.exceptions.NonExistentTeamCodeException;
import projekat.exceptions.takenCarNumberException;
import projekat.io.DriverIO;

/**
 *
 * @author Milica
 */
public class DriverUtil {

    /**
     * Metoda omogućuje korisniku da svim vozačima iz liste definiše ulogu u
     * timu
     *
     * @param driverList - lista svih vozača
     */
    public static void defineDriverRole(List<Driver> driverList) {
        for (Driver driver : driverList) {
            defineIndividualDriversRole(driver);
        }
    }

    /**
     * Metoda omogućava korisniku da definiše vozaču ulogu u timu
     *
     * @param d - vozač kom se definiše uloga
     */
    public static void defineIndividualDriversRole(Driver d) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println(d);
                System.out.println("Input a number of DriverRole:");
                System.out.println(" 1 - " + DriverRole.Regular + "\n 2 - " + DriverRole.Reserve + "\n 0 - " + DriverRole.Undefined);
                String str = scan.nextLine();
                if (str == null) {
                    throw new NonExistentDriverRoleException("You have to input a number between 0-2");
                }
                int i = Integer.parseInt(str);

                if (i == 0) {
                    d.setRole(DriverRole.Undefined);
                    bool = false;
                }
                if (i == 1) {
                    d.setRole(DriverRole.Regular);
                    bool = false;
                }
                if (i == 2) {
                    d.setRole(DriverRole.Reserve);
                    bool = false;
                }
                if (i < 0 || i > 2) {
                    throw new NonExistentDriverRoleException("You have to input a number between 0-2");
                }
            } catch (NonExistentDriverRoleException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number between 0-2");
            }
        }
    }

    /**
     * Omogućava korisniku da doda novog vozača
     *
     * @param driverList - lista vozača gde se dodaje novi vozač
     * @param teamList - lista timova - jednom od timova će biti dodat novi
     * vožač
     */
    public static void addNewDriver(List<Driver> driverList, List<Team> teamList) {
        Scanner scan = new Scanner(System.in);
        String name = "";
        String lastName = "";
        int carNumber = 0;
        String countryCode = "";
        String teamCode = "";
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("Input drivers name: ");
                name = scan.nextLine();
                System.out.println("Input drivers last name: ");
                lastName = scan.nextLine();
                for (Driver driver : driverList) {
                    if (driver.getName().equals(name) && driver.getLastName().equals(lastName)) {
                        throw new DriverAlreadyExistsException("Driver already exists! Try again.");
                    }
                }
                bool = false;
            } catch (DriverAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }
        bool = true;
        while (bool) {
            try {
                System.out.println("Input drivers country code: (3 characters)");
                countryCode = scan.nextLine();
                if (!(countryCode.length() == 3)) {
                    throw new CountryCodeException("Country code must consist of 3 characters! Try again.");
                }
                bool = false;
            } catch (CountryCodeException e) {
                System.out.println(e.getMessage());
            }
        }
        bool = true;
        while (bool) {
            try {
                System.out.println("Input drivers number: ");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidCarNumberException("You have to input a number between 1-99");
                }
                carNumber = Integer.parseInt(str);
                if (carNumber <= 0 || carNumber >= 100) {
                    throw new InvalidCarNumberException("Invalid car number! Try again. Number must be between 1 and 99");
                }
                for (Driver driver : driverList) {
                    if (carNumber == driver.getCarNumber()) {
                        throw new takenCarNumberException("Number is already taken! Try again.");
                    }
                }

                bool = false;
            } catch (InvalidCarNumberException | takenCarNumberException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number between 1-99");
            }
        }
        bool = true;
        while (bool) {
            try {
                int i = 1;
                for (Team team : teamList) {
                    System.out.println(team.getName() + " - " + team.getTeamCode());
                    i++;
                }
                System.out.println("Enter team code listed above: ");
                teamCode = scan.nextLine();
                if (!(teamCode.length() == 3)) {
                    throw new InvalidTeamCodeException("Team code must have 3 characters! Try again.");
                }
                int j = 0;
                for (Team team : teamList) {
                    if ((teamCode.toLowerCase().equals(team.getTeamCode().toLowerCase()))) {
                        bool = false;
                    } else {
                        j++;
                    }
                }
                if (j == teamList.size()) {
                    throw new NonExistentTeamCodeException("Team code you entered doesn't exist!Try again.");
                }
            } catch (InvalidTeamCodeException | NonExistentTeamCodeException e) {
                System.out.println(e.getMessage());
            }
        }
        Driver d = new Driver(name, lastName, carNumber, countryCode.toUpperCase(), teamCode.toUpperCase());
        defineIndividualDriversRole(d);
        driverList.add(d);
        TeamUtil.addIndividualDriverToTeam(d, teamList);
        DriverIO.printDriverToFile(d, DriverIO.SUPERLICENCE);
        //System.out.println(d + " " + d.getRole());

    }

    /**
     * Omogućava korisniku da pretražuje vozača prema broju automogila
     *
     * @param driverList - lista svih vozača
     * @return tražen vozač
     */
    public static Driver searchByCarNumber(List<Driver> driverList) {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            try {
                System.out.println("Input a car number: ");
                String str = scan.nextLine();
                if (str == null) {
                    throw new InvalidCarNumberException("You have to input a number between 1-99");
                }
                int number = Integer.parseInt(str);
                if (number <= 0 || number >= 100) {
                    throw new InvalidCarNumberException("Invalid car number! Try again. Number must be between 1 and 99");
                }
                int numOfDriv = 0;
                for (Driver driver : driverList) {
                    if (number == driver.getCarNumber()) {
                        return driver;
                    } else {
                        numOfDriv += 1;
                    }
                }
                if (numOfDriv == driverList.size()) {
                    throw new NonExistentCarNumber("Driver doesn't exists. Try again.");
                }

            } catch (InvalidCarNumberException | NonExistentCarNumber e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("You have to input a number between 1-99");
            }
        }
        return null;
    }

    //
}
