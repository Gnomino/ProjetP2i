package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.db.Attempt;
import fr.insalyon.p2i2.javaarduino.db.PlayedNote;
import fr.insalyon.p2i2.javaarduino.db.Position;
import fr.insalyon.p2i2.javaarduino.ihm.FenetreJeu;
import fr.insalyon.p2i2.javaarduino.ihm.PartitionPanel;

import java.awt.*;
import java.util.TimerTask;

public class TickTask extends TimerTask {
    private int tickID, waitingSince, pauseTicks;
    private double playedFrequency;
    private FenetreJeu fenetreJeu;
    private Attempt curAttempt;
    public TickTask(FenetreJeu fenetreJeu) {
        this.tickID = -1;
        this.waitingSince = 0;
        this.pauseTicks = 0;
        this.fenetreJeu = fenetreJeu;
        curAttempt = new Attempt(fenetreJeu.getMusic());
    }
    @Override
    public void run() {
        tickID++;
        System.out.println(tickID);
        if(tickID % TickTimer.FFT_PERIOD == 0) {
            playedFrequency = SoundProcessing.sampleMainFrequency();
            fenetreJeu.getFrequencyLabel().setText((int)playedFrequency + " Hz");
        }
        Position curPos = null;
        double curTime = getMusicTime();
        if(curTime > fenetreJeu.getPartition().getMusicDuration()) {
            fenetreJeu.getTickTimer().end(curAttempt);
            return;
        }
        for(Position p : fenetreJeu.getPartition().getPositionList()) {
            if(Math.abs(p.time-curTime) < TickTimer.TICK_PERIOD/1000.) {
                curPos = p;
            }
        }
        if(curPos != null && curPos.getDisplayColor() == Color.BLACK) { // The note is black if it hasn't been tried yet
            if(curPos.note.hasFrequency(playedFrequency)) {
                curPos.setDisplayColor(Color.GREEN);
                waitingSince = 0;
                addPlayedNote((int) Math.round(playedFrequency));
            }
            else if (waitingSince < TickTimer.TOLERANCE_TICKS) {
                waitingSince++;
                pauseTicks++;
            }
            else {
                curPos.compareAndSetColor((int) Math.round(playedFrequency));
                waitingSince = 0;
                addPlayedNote((int) Math.round(playedFrequency));
            }
        }
        fenetreJeu.repaint();
    }

    /*
        Returns the time position of the cursor in the music partition
     */
    public double getMusicTime() {
        return ((tickID-pauseTicks)*TickTimer.TICK_PERIOD)/1000.;
    }
    /*
        Returns the real time spent since the beginning of the music
     */
    public int getTime() {
        return tickID*TickTimer.TICK_PERIOD;
    }
    public void addPlayedNote(int playedFrequency) {
        PlayedNote playedNote = new PlayedNote(playedFrequency, (int) getMusicTime());
        curAttempt.getPlayedNotes().add(playedNote);
    }
}
