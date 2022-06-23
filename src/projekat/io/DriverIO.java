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
import projekat.data.Driver;

/**
 *
 * @author Milica
 */
public class DriverIO {

    public final static String SUPERLICENCE = "superlicence.txt";

    /**
     * Metoda učitava vozače iz fajla superlicence.txt. Kreira nove instance
     * klase Driver i dodaje ih u listu svih vozača.
     *
     * @return - lista svih učitanih vozača
     */
    public static List<Driver> loadDrivers() {
        BufferedReader reader = null;
        List<Driver> driverList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(SUPERLICENCE));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("/");
                //str[0] - name; str[1] - last name; str[2] - car number; str[3] - country code; str[4] - team code
                Driver driver = new Driver(str[0], str[1], Integer.parseInt(str[2]), str[3], str[4]);
                driverList.add(driver);
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
        return driverList;
    }

   /**
    * Metoda štampa pojedinačnog vozača u fajl
    * @param driver - vozač
    * @param filename - fajl u koji se upisuje vozač
    */
    public static void printDriverToFile(Driver driver, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(driver.getName() + "/" + driver.getLastName() + "/" + driver.getCarNumber() + "/" + driver.getCountryCode() + "/" + driver.getTeamCode());
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
     * Metoda koja štampa sve vozače sa njihovim ulogama sa liste vozača
     * @param driverList - lista svih vozača
     */
    public static void printAllDrivers(List<Driver> driverList) {
        for (Driver driver : driverList) {
            System.out.println(driver + " " + driver.getRole());
        }
    }

}
