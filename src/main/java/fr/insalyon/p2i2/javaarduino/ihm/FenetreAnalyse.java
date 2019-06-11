package fr.insalyon.p2i2.javaarduino.ihm;

import fr.insalyon.p2i2.javaarduino.AnalyseTimer;

import javax.swing.*;

public class FenetreAnalyse {
    private AnalyseTimer analyseTimer;
    private JFrame frame;
    public FenetreAnalyse(int nbNotes) {
        frame = new JFrame("FenetreAnalyse");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        analyseTimer = new AnalyseTimer(this, nbNotes);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getFrequenceLabel() {
        return frequenceLabel;
    }

    public JLabel getNotesLabel() {
        return notesLabel;
    }
    public void close() {
        frame.dispose();
    }
    private JProgressBar progressBar;
    private JLabel frequenceLabel;
    private JLabel notesLabel;
    private JPanel mainPanel;
}
