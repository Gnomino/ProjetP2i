/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.p2i2.javaarduino.ihm;

import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.db.Note;
import fr.insalyon.p2i2.javaarduino.db.Position;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author bkermani, svaure
 */
public class PartitionPanel extends JPanel {
    private final HashMap<String, Note> notes;
    private final int NB_CENTRAL_LINES = 6;
    private final int PARTITION_HEIGHT = 100;
    private final int NB_PARTITIONS = 3;
    private final int INTER_PARTITION_SPACE = 0;
    private final int PARITION_WIDTH = 940;
    private FenetreJeu fj;

    public int getMusicDuration() {
        return musicDuration;
    }

    private int musicDuration;
    private int distancePerUnitTime;
    private int lineSize;

    public LinkedList<Position> getPositionList() {
        return positionList;
    }

    private LinkedList<Position> positionList;

    public PartitionPanel(FenetreJeu fj) throws SQLException {
        this.fj = fj;
        int minF = 1000000, maxF = 0;
        notes = MusicDatabase.getAllNotes();
        /*for (Note n : notes.values()) {
            if (minF > n.frequency) minF = n.frequency;
            if (maxF < n.frequency) maxF = n.frequency;
        }
        LOWEST_FREQUENCY = minF;
        HIGHEST_FREQUENCY = maxF; */
        positionList = MusicDatabase.getNotesForMusic(fj.getMusic());
        for (Position p : positionList) {
            if (musicDuration < p.time) musicDuration = p.time+1;
        }
        distancePerUnitTime = (NB_PARTITIONS * PARITION_WIDTH) / musicDuration;
        lineSize = PARTITION_HEIGHT / (NB_CENTRAL_LINES);
    }
    private int[] getBaseCoordinates(double t) {
        int[] res = new int[2];
        int curPartition = (int)(t * (((double)NB_PARTITIONS) / musicDuration));
        res[0] = (int) (t * distancePerUnitTime) % ((musicDuration * distancePerUnitTime) / NB_PARTITIONS);
        res[1] = ((curPartition - 1) * INTER_PARTITION_SPACE) + ((curPartition + 1) * PARTITION_HEIGHT);
        return res;
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        // Ajout de 3 partitions sur l'IHM
        g.setColor(Color.black);
        for (int iPartition = 0; iPartition < NB_PARTITIONS; iPartition++) {
            int bottomHeight = (iPartition * INTER_PARTITION_SPACE) + ((iPartition + 1) * PARTITION_HEIGHT);
            for (int iCentralLine = 1; iCentralLine <= NB_CENTRAL_LINES; ++iCentralLine) {
                int lineHeight = bottomHeight - ((iCentralLine) * lineSize);
                g.drawLine(20, lineHeight, 20 + PARITION_WIDTH, lineHeight);
            }
        }
        // Ajout du curseur :
        double t = fj.getCursorTime();

        int[] cursorPos = getBaseCoordinates(t);
        System.out.println(t + ", " + cursorPos[0] + ", " + cursorPos[1]);
        g.drawLine(cursorPos[0], cursorPos[1], cursorPos[0], cursorPos[1] - PARTITION_HEIGHT);
        // Ajout des notes :

        for(Position p : positionList) {
            g.setColor(p.getDisplayColor());
            int[] notePos = getBaseCoordinates(p.time);
            g.drawString(p.note.name, notePos[0], notePos[1]);
            notePos[1] -= PARTITION_HEIGHT*((1+NB_CENTRAL_LINES-(double) p.note.corde)/NB_CENTRAL_LINES);
            g.setColor(Color.WHITE);
            g.fillOval(notePos[0], notePos[1], lineSize, lineSize);
            g.setColor(p.getDisplayColor());
            g.drawString(""+p.note.frette, notePos[0]+5, notePos[1]+10);
        }
        g.setColor(Color.BLACK);
    }
}
