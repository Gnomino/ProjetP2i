package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.db.Attempt;
import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.ihm.FenetreJeu;

import java.sql.SQLException;
import java.util.Timer;

public class TickTimer extends Timer {
    public static final int TICK_PERIOD = 20; // in milliseconds
    public static final int FFT_PERIOD = 5; // the FFT is computed every FFT_PERIOD ticks;
    public static final int TOLERANCE_TICKS = 50; // if not played properly, a note will take TOLERANCE_TICKS ticks to be skipped

    public TickTask getTickTask() {
        return tickTask;
    }

    private TickTask tickTask;

    public TickTimer(FenetreJeu fenetreJeu) {
        tickTask = new TickTask(fenetreJeu);
    }
    public void start() {
        scheduleAtFixedRate(tickTask, 1000, TICK_PERIOD);
    }
    public void end(Attempt attempt) {
        cancel();
        purge();
        try {
            MusicDatabase.insertAttempt(attempt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("=== FIN ===");
    }
}
