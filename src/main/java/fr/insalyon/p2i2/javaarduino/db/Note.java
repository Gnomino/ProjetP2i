package fr.insalyon.p2i2.javaarduino.db;

import java.awt.*;

public class Note {
    public final int frequency;
    public final String name;
    private Color displayColor = Color.BLACK;

    public Note(int frequency, String name) {
        this.frequency = frequency;
        this.name = name;
    }

    public boolean hasFrequency(double frequency) {
        return Math.abs(frequency - this.frequency) <= 20; // On tolère de 20 Hz de différence entre 2 notes
    }

    public String toString() {
        return "Note \"" + name + "\" : fréquence " + frequency + " Hz";
    }


    public Color getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(Color displayColor) {
        this.displayColor = displayColor;
    }
    public void compareAndSetColor(int playedFrequency) {
        if(hasFrequency(playedFrequency)) {
            displayColor = Color.GREEN;
        }
        else {
            displayColor = Color.ORANGE;
        }
    }
}
