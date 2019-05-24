package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.usb.ArduinoManager;
import fr.insalyon.p2i2.javaarduino.util.Console;

import java.io.IOException;
import java.sql.SQLException;

public class MainArduino
{
    
    public static void main(String[] args) {

        // Objet matérialisant la console d'exécution (Affichage Écran / Lecture Clavier)
        final Console console = new Console();
        try {
            MusicDatabase.initiateConnection("PC-TP-MYSQL.insa-lyon.fr:3306", "G224_C_BD1", "G224_C", "G224_C"); // Note: It's a bad practice to share code that contains credentials
        } catch (SQLException e) {
            System.out.println("Impossible de se connecter à MySQL : ");
            e.printStackTrace();
            return;
        }
        // Affichage sur la console
        console.log("DÉBUT du programme MainArduino");

        console.log("TOUS les Ports COM Virtuels:");
        for (String port : ArduinoManager.listVirtualComPorts()) {
            console.log(" - " + port);
        }
        console.log("----");

        // Recherche d'un port disponible (avec une liste d'exceptions si besoin)
        String myPort = ArduinoManager.searchVirtualComPort("COM0", "/dev/tty.usbserial-FTUS8LMO", "<autre-exception>");

        console.log("CONNEXION au port " + myPort);

        ArduinoManager arduino = new ArduinoManager(myPort) {
            int totalNbSamples = 0;
            @Override
            protected void onData(String line) {

                // Cette méthode est appelée AUTOMATIQUEMENT lorsque l'Arduino envoie des données
                // Affichage sur la Console de la ligne transmise par l'Arduino
                // console.println("ARDUINO >> " + line);
                if(line.length() == 0)
                    return;
                try {
                    SoundProcessing.addAmplitude(Integer.parseInt(line));
                }
                catch (NumberFormatException e) {
                    console.log(e.getLocalizedMessage());
                }
                totalNbSamples++;
                if(totalNbSamples%400 == 0) {
                    double mainFrequency = SoundProcessing.sampleMainFrequency();
                }
                // À vous de jouer ;-)
                // Par exemple:
                //   String[] data = line.split(";");
                //   int sensorid = Integer.parseInt(data[0]);
                //   double value = Double.parseDouble(data[1]);
                //   ...
            // console.println("test ");

            }
        };

        try {

            console.log("DÉMARRAGE de la connexion");
            // Connexion à l'Arduino
            arduino.start();

            console.log("BOUCLE infinie en attente du Clavier");
            // Boucle d'ecriture sur l'arduino (execution concurrente au thread)
            boolean exit = false;

            while (!exit) {

                // Lecture Clavier de la ligne saisie par l'Utilisateur
                String line = console.readLine("Envoyer une ligne (ou 'stop') > ");

                if (line.length() != 0) {

                    // Affichage sur l'écran
                    console.log("CLAVIER >> " + line);

                    // Test de sortie de boucle
                    exit = line.equalsIgnoreCase("stop");

                    if (!exit) {
                        // Envoi sur l'Arduino du texte saisi au Clavier
                        arduino.write(line);
                    }
                }
            }

            console.log("ARRÊT de la connexion");
            // Fin de la connexion à l'Arduino
            arduino.stop();

        } catch (IOException ex) {
            // Si un problème a eu lieu...
            console.log(ex);
        }

    }
}
