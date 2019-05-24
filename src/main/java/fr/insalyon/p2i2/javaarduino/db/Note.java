package fr.insalyon.p2i2.javaarduino.db;

public class Note {
    public final int frequency;
    public final String name;

    public Note(int frequency, String name) {
        this.frequency = frequency;
        this.name = name;
    }

    public boolean hasFrequency(double frequency) {
        return Math.abs(frequency - this.frequency) <= 20; // On tolère de 20 Hz de différence entre 2 notes
    }
}
