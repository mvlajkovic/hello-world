/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milica
 */
public abstract class Championship {

    private int sezona;

    List<Round> championshipRounds = new ArrayList<>();

    public Championship() {
    }

    public Championship(int sezona) {
        this.sezona = sezona;
    }

    public Championship(Championship ch) {
        this.sezona = ch.sezona;
    }

    public int getSezona() {
        return sezona;
    }

    public void setSezona(int sezona) {
        this.sezona = sezona;
    }

    public List<Round> getChampionshipRounds() {
        return championshipRounds;
    }

    public void setChampionshipRounds(List<Round> championshipRounds) {
        this.championshipRounds = championshipRounds;
    }
    
    public abstract void readStandings();

    public abstract void printStandingsToFile();

    public abstract void declareChampion();

    public abstract void readChampion();

    public abstract void printChampionToFile();

    @Override
    public String toString() {
        return "Championship{" + "sezona=" + sezona + '}';
    }

}
