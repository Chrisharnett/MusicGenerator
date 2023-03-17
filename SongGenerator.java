package mylearning.assignment2;

import javax.sound.midi.*;
import java.util.Scanner;

/**
 * @author saxDev
 * @studentnumber 20188141
 * User sets an upper limit of n  fibonacci numbers
 * Each fibonacci number impacts a random variable (note, length, etc)
 * Cycles through set of n fibonacci numbers.
 **/
public class SongGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Original had a class to choose instruments. don't think it's needed.
        //Instruments instruments = new Instruments();
        System.out.println("Here comes some music");
        //TODO user options to set limits of fib/primes, etc. for now I'll hard code

        int bpm = 120;
        String songName = "test";

        WriteMidi.writeMidi(WriteMidi.play(bpm), songName);

    }
}

