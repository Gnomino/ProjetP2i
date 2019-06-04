/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.p2i2.javaarduino.ihm;

import java.awt.Color;

/**
 *
 * @author bkermani
 */
public class Note {
    String nom;
    int freq;
    Color couleur;
    
 

    public Note(int freq, String nom) {
        this.freq = freq;
        this.nom = nom;
    }

    public boolean hasFrequency(double frequency) {
        return Math.abs(frequency - this.freq) <= 20; // On tolère de 20 Hz de différence entre 2 notes
    }

    public String toString() {
        return "Note \"" + nom + "\" : fréquence " + freq + " Hz";
    }
    
 
    //Pareil que hasFrequency
    public boolean estJoueeCorrectement(Note notejouee){
        int intervalleConfiance =30;
        if(this.freq<= (notejouee.freq + intervalleConfiance) && this.freq >= (notejouee.freq - intervalleConfiance)){
            return true;
        }else{
            return false;
        }
    }
    public void setColor(Note notejouee){
        if(estJoueeCorrectement(notejouee)){
            this.couleur = Color.GREEN;
         
        }
    }
}
