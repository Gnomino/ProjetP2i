/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.p2i2.javaarduino.ihm;
import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.db.Note;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author bkermani
 */
public class PartitionPanel extends JPanel{
    private FenetreJeu fj;
    
    public PartitionPanel(FenetreJeu fj){
        this.fj = fj;
    }
    public void paint(Graphics g){
        // Ajout de 3 partitions sur l'IHM
        for(int i = 20 ; i<=70 ; i=i+10){
            g.drawLine(20, i, 960, i);
        }
        
        for(int i = 115 ; i<=165 ; i=i+10){
            g.drawLine(20, i, 960, i);
        }
        
        for(int i = 200 ; i<=250 ; i=i+10){
            g.drawLine(20, i, 960, i);
        }
        //On se connecte à la BD pour avoir accès à certains paramètres
        HashMap<String, fr.insalyon.p2i2.javaarduino.db.Note> noteHashMap;
        try {
             //
            // Note: It's a bad practice to share code that contains credentials
            noteHashMap = MusicDatabase.getAllNotes();
            for (Note n : noteHashMap.values()) {
                System.out.println(n);
            }

        }catch(SQLException e) {
            System.out.println("Impossible de se connecter à MySQL : ");
            e.printStackTrace();
            return;
        }
        
        
        
   }
}
