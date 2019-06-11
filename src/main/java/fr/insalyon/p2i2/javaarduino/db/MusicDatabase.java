package fr.insalyon.p2i2.javaarduino.db;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Handles the link with the MySQL server, as well as all R/W requests on the database.
 */
public class MusicDatabase {
    private static Connection connection = null;
    private static HashMap<String, Note> notes = null;

    /**
     * Initiates a connection to the MySQL server. Necessary for any further operation.
     *
     * @param host     The host and port of the server (eg: localhost:3306)
     * @param db
     * @param username
     * @param password
     * @throws SQLException
     */
    public static void initiateConnection(String host, String db, String username, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, username, password);
    }

    /**
     * @return A LinkedList of all Music objects from the database
     * @throws SQLException
     */
    public static LinkedList<Music> getAllMusic() throws SQLException {
        LinkedList<Music> res = new LinkedList<>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Musique ORDER BY idMusique ASC");
        while (rs.next()) {
            res.add(new Music(rs.getInt("idMusique"), rs.getInt("duree"), rs.getString("nomMusique"), rs.getString(
                    "nomArtiste")));
        }
        return res;
    }

    /**
     * @param idMusique
     * @return A Music object corresponding to the given idMusique
     * @throws SQLException
     */
    public static Music getMusicById(int idMusique) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Musique WHERE idMusique = ?");
        preparedStatement.setInt(1, idMusique);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return new Music(rs.getInt("idMusique"), rs.getInt("duree"), rs.getString("nomMusique"), rs.getString(
                    "nomArtiste"));
        }
        return null;
    }

    public static Music getMusicByTitle(String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Musique WHERE nomMusique = ?");
        preparedStatement.setString(1, title);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return new Music(rs.getInt("idMusique"), rs.getInt("duree"), rs.getString("nomMusique"), rs.getString(
                    "nomArtiste"));
        }
        return null;
    }

    /**
     * Fetches all notes from the database
     *
     * @return A HashMap that associates a note name (eg: A#4) to a Note object
     * @throws SQLException
     */
    public static HashMap<String, Note> getAllNotes() throws SQLException {
        if (notes != null) return notes;
        HashMap<String, Note> res = new HashMap<>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Note ORDER BY freq ASC");
        while (rs.next()) {
            res.put(rs.getString("nomNote"), new Note(rs.getInt("freq"), rs.getString("nomNote"), rs.getInt("corde"), rs.getInt("frette")));
        }
        notes = res;
        return res;
    }

    /**
     * @param music
     * @return A LinkedList of Position objects, representing the position of each note in the song
     * @throws SQLException
     */
    public static LinkedList<Position> getNotesForMusic(Music music) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Position,Note WHERE " +
                "Position" + ".nomNote = Note.nomNote and Position.idMusique = ? ORDER BY Position.ordre ASC");
        preparedStatement.setInt(1, music.getIdMusic());

        ResultSet rs = preparedStatement.executeQuery();
        LinkedList<Position> res = new LinkedList<>();
        while (rs.next()) {
            res.add(new Position(music, notes.get(rs.getString("Note.nomNote")), rs.getInt("ordre")));
        }
        return res;
    }

    /**
     * Inserts an attempt and its associated played notes into the database
     *
     * @param attempt The attempt to insert into the database
     * @return The attempt's ID generated by MySQL
     * @throws SQLException
     */
    public static int insertAttempt(Attempt attempt) throws SQLException {
        PreparedStatement attemptInsertStatement =
                connection.prepareStatement("INSERT INTO Tentative (idMusique) " + "VALUES(?)",
                        Statement.RETURN_GENERATED_KEYS);
        if(attempt.music == null) {
            attemptInsertStatement.setInt(1, 0);
        }
        else
            attemptInsertStatement.setInt(1, attempt.music.getIdMusic());
        attemptInsertStatement.executeUpdate();
        ResultSet generatedKeys = attemptInsertStatement.getGeneratedKeys();
        generatedKeys.next();
        int attemptId = generatedKeys.getInt(1);
        PreparedStatement playedNoteInsertStatement = connection.prepareStatement("INSERT INTO NoteJouee (temps, " +
                "freqJouee, idTentative) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        for (PlayedNote playedNote : attempt.getPlayedNotes()) { // If the played note is not in the database yet
            if (playedNote.getIdPlayedNote() == -1) {
                playedNoteInsertStatement.setInt(1, playedNote.getTimePlayed());
                playedNoteInsertStatement.setInt(2, playedNote.getFrequency());
                playedNoteInsertStatement.setInt(3, attemptId);
                playedNoteInsertStatement.executeUpdate();
                /*
                playedNote.setIdPlayedNote(playedNoteInsertStatement.getGeneratedKeys().getInt(1));*/
            }
        }
        return attemptId; // Returns the idTentative generated by MySQL
    }

    /**
     * @return A LinkedList of all recorded attempts
     * @throws SQLException
     */
    public static LinkedList<Attempt> getAllAttempts() throws SQLException {
        LinkedList<Attempt> res = new LinkedList<>();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Tentative, Musique, NoteJouee " +
                "WHERE " + "Tentative.idMusique = Musique.idMusique AND NoteJouee.idTentative = Tentative" +
                ".idTentative ORDER BY Tentative.idTentative, NoteJouee.temps ASC");
        int currentID = -1;
        LinkedList<PlayedNote> playedNotes = null;
        while (rs.next()) {
            int attemptId = rs.getInt("Tentative.idTentative");
            if (attemptId != currentID) {
                if (currentID != -1) {
                    res.add(new Attempt(rs.getInt("Tentative.idTentative"), new Music(rs.getInt("Musique.idMusique"),
                            rs.getInt("Musique.duree"), rs.getString("Musique.nomMusique"), rs.getString("Musique" +
                            ".nomAriste")), playedNotes));
                }
                playedNotes = new LinkedList<>();
                currentID = attemptId;
            }
            playedNotes.add(new PlayedNote(rs.getInt("NoteJouee.idNoteJouee"), rs.getInt("NoteJouee.freqJouee"),
                    rs.getInt("NoteJouee.temps")));
        }
        return res;
    }
}
