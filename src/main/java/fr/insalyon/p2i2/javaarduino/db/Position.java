package fr.insalyon.p2i2.javaarduino.db;

public class Position {
    public final Music music;
    public final Note note;
    public final int time;

    public Position(Music music, Note note, int time) {
        this.music = music;
        this.note = note;
        this.time = time;
    }
}
