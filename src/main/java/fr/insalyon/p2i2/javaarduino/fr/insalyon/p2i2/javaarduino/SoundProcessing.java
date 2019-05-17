package fr.insalyon.p2i2.javaarduino.fr.insalyon.p2i2.javaarduino;

import org.jtransforms.fft.DoubleFFT_1D;

import java.util.LinkedList;

public class SoundProcessing {
    private static LinkedList<Integer> soundSample = new LinkedList<Integer>();
    private static final int NB_SAMPLES = 200;
    private static final int SAMPLING_FREQUENCY = 2000;
    public static void addAmplitude(int a) {
        soundSample.add(a);
        if(soundSample.size() >= NB_SAMPLES) {
            soundSample.removeFirst();
        }
    }
    public static double sampleMainFrequency() {
        final int nbSamples = Math.min(NB_SAMPLES, soundSample.size());
        DoubleFFT_1D doubleFFT = new DoubleFFT_1D(nbSamples);
        double[] a = new double[nbSamples * 2];
        for(int i = 0; i < nbSamples; ++i) {
            a[2*i] = soundSample.get(i);
            a[(2*i)+1] = 0;
        }
        doubleFFT.complexForward(a);
        double maxMagnitude = Double.NEGATIVE_INFINITY;
        double maxFrequency = -1;
        for(int i = 1; i < nbSamples; ++i) {
            double re = a[2*i];
            double im = a[(2*i)+1];
            double magnitude = (re*re + im*im);
            if(magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
                maxFrequency = SAMPLING_FREQUENCY * (((double) i)/(double) nbSamples);
                System.out.println("--- mag : " + maxMagnitude + " for freq : " + maxFrequency + " i : " + i);
            }
        }

        return maxFrequency;
    }
}
