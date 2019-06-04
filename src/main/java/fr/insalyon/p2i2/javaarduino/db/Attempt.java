package fr.insalyon.p2i2.javaarduino.db;

import java.sql.SQLException;
import java.util.LinkedList;

public class Attempt {
    public final Music music;
    private int idAttempt;
    private LinkedList<PlayedNote> playedNotes;

    public Attempt(Music music) {
        this.music = music;
        playedNotes = new LinkedList<>();
    }

    public Attempt(int idAttempt, Music music, LinkedList<PlayedNote> playedNotes) {
        this.idAttempt = idAttempt;
        this.music = music;
        this.playedNotes = playedNotes;
    }

    private void insert() throws SQLException {
        idAttempt = MusicDatabase.insertAttempt(this);

    }

    public LinkedList<PlayedNote> getPlayedNotes() {
        return playedNotes;
    }
}
