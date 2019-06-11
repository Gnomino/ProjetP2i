package fr.insalyon.p2i2.javaarduino.db;

public class PlayedNote {
    private int idPlayedNote;
    private int frequency;
    private int timePlayed;

    public PlayedNote(int frequency, int timePlayed) {
        this.idPlayedNote = -1; // The note is not in the database yet
        this.frequency = frequency;
        this.timePlayed = timePlayed;
    }

    public PlayedNote(int idPlayedNote, int frequency, int timePlayed) {
        this.idPlayedNote = idPlayedNote;
        this.frequency = frequency;
        this.timePlayed = timePlayed;
    }

    public int getIdPlayedNote() {
        return idPlayedNote;
    }

    public void setIdPlayedNote(int idPlayedNote) {
        this.idPlayedNote = idPlayedNote;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }
}
