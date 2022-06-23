/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

/**
 *
 * @author Milica
 */
public class Driver {
    private String name;
    private String lastName;
    private int carNumber;
    private String countryCode;
    private String teamCode;
    private DriverRole role = DriverRole.Undefined;

    public Driver() {
    }

    public Driver(String name, String lastName, int carNumber, String countryCode, String teamCode) {
        this.name = name;
        this.lastName = lastName;
        this.carNumber = carNumber;
        this.countryCode = countryCode;
        this.teamCode = teamCode;
    }
    
    public Driver(Driver d) {
        this.name = d.name;
        this.lastName = d.lastName;
        this.carNumber = d.carNumber;
        this.countryCode = d.countryCode;
        this.teamCode = d.teamCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public DriverRole getRole() {
        return role;
    }

    public void setRole(DriverRole role) {
        this.role = role;
    }
    

    @Override
    public String toString() {
        return name + " " + lastName + " " + carNumber + " " + countryCode + " " + teamCode;
    }
    
    
    
}
