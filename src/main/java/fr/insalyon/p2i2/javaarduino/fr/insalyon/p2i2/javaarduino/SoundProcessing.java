package fr.insalyon.p2i2.javaarduino.fr.insalyon.p2i2.javaarduino;

import java.util.LinkedList;

public class SoundProcessing {
    private static LinkedList<Integer> soundSample = new LinkedList<Integer>();
    private static final int NB_SAMPLES = 200;
    public static void addAmplitude(int a) {
        soundSample.add(a);
        if(soundSample.size() >= NB_SAMPLES) {
            soundSample.removeFirst();
        }
    }
    public static double sampleMainFrequency() {
        return 0;
    }
}
