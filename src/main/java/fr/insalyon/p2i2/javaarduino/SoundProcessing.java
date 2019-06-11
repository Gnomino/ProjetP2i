package fr.insalyon.p2i2.javaarduino;

import org.jtransforms.fft.DoubleFFT_1D;

import java.util.LinkedList;

public class SoundProcessing {
    public static final int NB_SAMPLES = 1000;
    private static final double SAMPLING_FREQUENCY = 2000.;
    private static LinkedList<Integer> soundSample = new LinkedList<Integer>();

    public static void addAmplitude(int a) {
        soundSample.add(a);
        if (soundSample.size() >= NB_SAMPLES) {
            soundSample.removeFirst();
        }
    }

    private static void printAmplitudes() {
        for (Integer integer : soundSample) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public static double sampleMainFrequency() {
        // printAmplitudes();
        final int nbSamples = Math.min(NB_SAMPLES, soundSample.size());
        if(nbSamples==0) {
            System.out.println("WARNING : no samples found");
            return 0;
        }
        DoubleFFT_1D doubleFFT = new DoubleFFT_1D(nbSamples);
        double[] a = new double[nbSamples * 2];
        for (int i = 0; i < nbSamples; ++i) {
            try {
                a[i] = soundSample.get(i);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("i : " + i);
            }
            //a[(2*i)+1] = 0;
        }
        doubleFFT.realForward(a);
        double maxMagnitude = Double.NEGATIVE_INFINITY;
        double maxFrequency = -1;
        for (int i = 1; i < nbSamples; ++i) {
            double re = a[2 * i];
            double im = a[(2 * i) + 1];
            double magnitude = (re * re + im * im);
            double frequency = SAMPLING_FREQUENCY * (((double) i) / (double) nbSamples);
            /* if(magnitude != 0)
                System.out.println("-- mag : " + magnitude + " for freq : " + frequency + " i : " + i); */
            if (magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
                maxFrequency = frequency;
            }
        }
        System.out.println("--- mag : " + maxMagnitude + " for freq : " + maxFrequency);
        return maxFrequency;
    }
}
