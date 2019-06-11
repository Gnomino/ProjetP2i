package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.ihm.FenetreAnalyse;

import java.util.Timer;

public class AnalyseTimer extends Timer {
    private FenetreAnalyse fenetreAnalyse;

    public int getGoal() {
        return goal;
    }

    private int goal;
    public AnalyseTimer(FenetreAnalyse fenetreAnalyse, int goal) {
        this.goal = goal;
        this.fenetreAnalyse = fenetreAnalyse;
        scheduleAtFixedRate(new AnalyseTask(this, fenetreAnalyse), 1000, 1000);
    }
}
