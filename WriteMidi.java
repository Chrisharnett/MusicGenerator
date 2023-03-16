package mylearning.assignment2;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

/**
 * @author saxDev
 * @studentnumber 20188141
 **/
public class WriteMidi {


    /**
     * Method to get a set of pitches (key) to use in building a melody.
     * @return key
     */
    public static int[] getKey(){
        //array of values to get things started.
        //TODO create class of keys that let user choose the keys and octaves used.
        int[] key = new int[]{63, 64, 66, 68, 69, 71, 73, 75, 76, 78};
        return key;
    }

    /**
     * Get a set of possible rhythms for the music.
     * @return possibleRhythms
     */
    public static int[] getRhythms(){
        //TODO create mpre interesting rhythm possibilities. Maybe even a new class.
        int[] possibleRhythms = new int[]{1920, 960, 480, 240};
        return possibleRhythms;
    }

    /**
     * Translate the values in a Fibonacci sequence into pitch values.
     * @return pitchArrayu
     */
    public static int[] pitches(){
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();
        int[] fibArray=fibonacciCalculator.getFibArray(10);
        String pitchString = "";
        for (int i = 0; i < fibArray.length; i++) {
            pitchString += (String.valueOf(fibArray[i]));
            }
        int[] pitchArray = new int[pitchString.length()];
        for (int j = 0; j < pitchString.length(); j++) {
            int note = pitchString.charAt(j);
            note = (WriteMidi.getKey())[note];
            pitchArray[j] = note;
        }
        return pitchArray;
    }

    /**
     * Method to derive rhythms from Prime Numbers.
     * @return
     */
    public static int[] rhythms(){
        PrimeNumberCalculator primeNumberCalculator = new PrimeNumberCalculator();
        String prime1 = primeNumberCalculator.primeString(23, 43);
        String[] primes = prime1.split(", ");
        int[] rhythms = new int[primes.length];
        for (int i = 0; i < primes.length; i++) {
            int ticks = (getRhythms()[(getRhythms().length) % Integer.parseInt(primes[i])]);
            rhythms[i] = ticks;
        }
        return rhythms;
    }

    /**
     * Method that creates a melody from an array of pitches and an array of rhythms. TODO Add velocity!
     */
    public static void createMelody(){
        int totalTicks;
        }

}
