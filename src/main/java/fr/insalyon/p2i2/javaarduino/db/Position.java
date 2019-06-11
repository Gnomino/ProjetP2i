package fr.insalyon.p2i2.javaarduino.db;

import java.awt.*;

public class Position {
    public final Music music;
    public final Note note;
    public final int time;

    private Color displayColor = Color.BLACK;

    public Position(Music music, Note note, int time) {
        this.music = music;
        this.note = note;
        this.time = time;
    }

    public Color getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(Color displayColor) {
        this.displayColor = displayColor;
    }

    public void compareAndSetColor(int playedFrequency) {
        if (note.hasFrequency(playedFrequency)) {
            displayColor = Color.GREEN;
        } else {
            displayColor = Color.ORANGE;
        }
    }
}
