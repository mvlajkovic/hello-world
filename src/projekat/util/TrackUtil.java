/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat.util;

import projekat.data.TrackDirection;
import projekat.data.TrackType;

/**
 *
 * @author Milica
 */
public class TrackUtil {

    /**
     * Metoda koja za unet string određuje tip staze
     * @param input - string 
     * @return tip staze
     */
    public static TrackType defineTrackType(String input) {
        TrackType type = TrackType.Undefined;
        if (input.toLowerCase().contains("street")) {
            type = TrackType.Street_Circuit;
        }
        if (input.toLowerCase().contains("road")) {
            type = TrackType.Road_Cirtcuit;
        }
        if (input.toLowerCase().contains("race")) {
            type = TrackType.Race_Cirtcuit;
        }
        return type;
    }

    /**
     * Metoda koja za unet string određuje smer staze
     * @param input - string
     * @return smer staze
     */
    public static TrackDirection defineTrackDirection(String input) {
        TrackDirection dir = TrackDirection.Undefined;
        if (input.toLowerCase().contains("clockwise")) {
            if (input.toLowerCase().contains("anti") || input.toLowerCase().contains("counter")) {
                dir = TrackDirection.Anti_Clockwise;
            } else {
                dir = TrackDirection.Clockwise;
            }
        }
        return dir;
    }
}
