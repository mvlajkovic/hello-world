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
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.util.TrackUtil;

/**
 *
 * @author Milica
 */
public class TrackIO {
    public final static String TRACKS = "tracks.txt";
    
    /**
     * Metoda učitava staze iz fajla tracks.txt. Kreira nove instance klase
     * Track i dodaje ih u listu svih staza.
     * @return - lista svih staza
     */ 
    public static List<Track> loadTracks() {
        BufferedReader reader = null;
        List<Track> trackList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(TRACKS));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("/");
                //str[0] - name; str[1] - location; str[2] - length; str[3]-type; str[4]-direction; 
                TrackType type = TrackUtil.defineTrackType(str[3]);
                TrackDirection dir = TrackUtil.defineTrackDirection(str[4]);
                Track track = new Track(str[0], str[1], Double.parseDouble(str[2]), type, dir);
                trackList.add(track);
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
        return trackList;
    }
    
    /**
     * Metoda štampa pojedinačnu stazu u fajl
     * @param track - staza koja se štampa u fajl
     * @param filename - naziv fajla u koji se štampa staza
     */
    public static void printTrackToFile(Track track, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(track.toString());
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
     * Metoda štampa sve staze redom
     * @param trackList - lista svih staza
     */
    public static void printAllTracks(List<Track> trackList){
         for (Track track : trackList) {
            System.out.println(track);
        }
     }
     
}
