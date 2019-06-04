/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.p2i2.javaarduino.ihm;

import fr.insalyon.p2i2.javaarduino.db.Music;
import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bkermani
 */
public class IHM_Guitare {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FenetreAccueil fa = new FenetreAccueil();
        try {
            MusicDatabase.initiateConnection("PC-TP-MYSQL.insa-lyon.fr:3306", "G224_C_BD1", "G224_C", "G224_C");
            LinkedList<Music> listeMusique = MusicDatabase.getAllMusic();
            for (Music m : listeMusique) {
                fa.getNomMusique().addItem(m.getTitle());
            }

            fa.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(IHM_Guitare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
