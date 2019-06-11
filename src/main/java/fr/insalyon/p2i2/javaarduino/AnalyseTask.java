package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.db.Attempt;
import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.db.PlayedNote;
import fr.insalyon.p2i2.javaarduino.ihm.FenetreAnalyse;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.TimerTask;

public class AnalyseTask extends TimerTask {
    private int sampleId = 0, sampleGoal;
    private AnalyseTimer timer;
    private FenetreAnalyse fenetreAnalyse;
    private Attempt attempt;
    public AnalyseTask(AnalyseTimer timer, FenetreAnalyse fenetreAnalyse) {
        this.timer = timer;
        this.sampleGoal = timer.getGoal();
        this.attempt = new Attempt(null);
        fenetreAnalyse.getProgressBar().setMaximum(sampleGoal);
        this.fenetreAnalyse = fenetreAnalyse;
        SoundProcessing.NB_SAMPLES = 500;
    }
    @Override
    public void run() {
        int playedFrequency = (int) SoundProcessing.sampleMainFrequency();
        ++sampleId;

        fenetreAnalyse.getProgressBar().setValue(sampleId);
        fenetreAnalyse.getNotesLabel().setText("Notes restantes : " + (sampleGoal-sampleId));
        fenetreAnalyse.getFrequenceLabel().setText("Fréquence : " + playedFrequency + " Hz");
        attempt.getPlayedNotes().add(new PlayedNote(playedFrequency, sampleId));

        if(sampleId == sampleGoal) {
            try {
                MusicDatabase.insertAttempt(attempt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            timer.cancel();
            timer.purge();

            fenetreAnalyse.close();
            SoundProcessing.NB_SAMPLES = 1000;
        }
    }
}
