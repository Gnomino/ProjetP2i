package fr.insalyon.p2i2.javaarduino.db;

public class Note {
    public final int frequency,corde,frette;
    public final String name;
    private static final int TOLERANCE = 20;
    public Note(int frequency, String name, int corde, int frette) {
        this.frequency = frequency;
        this.name = name;
        this.corde = corde;
        this.frette = frette;
    }

    public boolean hasFrequency(double frequency) {
        return Math.abs(frequency - this.frequency) <= TOLERANCE; // On tolère de 20 Hz de différence entre 2 notes
    }

    public String toString() {
        return "Note \"" + name + "\" : fréquence " + frequency + " Hz";
    }

}
