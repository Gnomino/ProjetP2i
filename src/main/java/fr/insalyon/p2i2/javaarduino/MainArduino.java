package fr.insalyon.p2i2.javaarduino;

import fr.insalyon.p2i2.javaarduino.db.MusicDatabase;
import fr.insalyon.p2i2.javaarduino.db.Note;
import fr.insalyon.p2i2.javaarduino.ihm.IHM_Guitare;
import fr.insalyon.p2i2.javaarduino.usb.ArduinoManager;
import fr.insalyon.p2i2.javaarduino.util.Console;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MainArduino {
    private static boolean keepLooping = true;
    public static void stopLoop() {
        keepLooping = false;
    }
    public static void main(String[] args) {

        final Console console = new Console();
        try {
            MusicDatabase.initiateConnection("PC-TP-MYSQL.insa-lyon.fr:3306", "G224_C_BD1", "G224_C", "G224_C"); //
            // Note: It's a bad practice to share code that contains credentials
            HashMap<String, Note> noteHashMap = MusicDatabase.getAllNotes();
            for (Note n : noteHashMap.values()) {
                System.out.println(n);
            }

        } catch (SQLException e) {
            System.out.println("Impossible de se connecter à MySQL : ");
            e.printStackTrace();
            return;
        }

        String myPort = ArduinoManager.searchVirtualComPort("COM0", "/dev/tty.usbserial-FTUS8LMO", "<autre-exception>");

        console.log("CONNEXION au port " + myPort);

        ArduinoManager arduino = new ArduinoManager(myPort) {
            // int totalNbSamples = 0;

            @Override
            protected void onData(String line) {
                if (line.length() == 0) return;
                try {
                    SoundProcessing.addAmplitude(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                /*if ((++totalNbSamples) % SoundProcessing.NB_SAMPLES == 0) {
                    double mainFrequency = SoundProcessing.sampleMainFrequency();
                }*/
            }
        };

        try {
            arduino.start();
            /* boolean exit = false;

            while (!exit) {
                String line = console.readLine("Envoyer une ligne (ou 'stop') > ");

                if (line.length() != 0) {
                    exit = line.equalsIgnoreCase("stop");

                    if (!exit) {
                        arduino.write(line);
                    }
                }
            } */
            IHM_Guitare.main(args);
            while(keepLooping) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console.log("ARRÊT de la connexion");
            arduino.stop();

        } catch (IOException ex) {
            console.log(ex);
        }
    }
}
