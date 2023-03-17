package mylearning.assignment2;

import javax.sound.midi.*;

import java.io.File;
import java.io.IOException;

import static java.lang.Math.min;

/**
 * @author saxDev
 * @studentnumber 20188141
 **/
public class WriteMidi {

    public static Sequence play(int bpm) {
        int timeSig = 44;
        int subDivisions = timeSig%10;
        int numMeasures = 8;
        Sequence sequence = null;
        try {
            sequence = new Sequence(Sequence.PPQ, subDivisions);
        } catch(InvalidMidiDataException e1) {
            e1.printStackTrace();
        }

        try {

            MidiChannel[] midiChannel;
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            midiChannel = synthesizer.getChannels();
            midiChannel[0].programChange(01); //hard coded for now
            midiChannel = synthesizer.getChannels();

            Sequencer sequencer = MidiSystem.getSequencer();
            Transmitter sequencerTransmitter = sequencer.getTransmitter();
            Receiver synthReceiver = synthesizer.getReceiver();
            sequencerTransmitter.setReceiver(synthReceiver);

            sequencer.open();
//            sequence = new Sequence(Sequence.PPQ, 4);
            WriteMidi.createMelody(sequence, WriteMidi.getKey(), 44, numMeasures);
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(bpm);
            sequencer.start();



            //TODO Create an output of the midi to play on external equipment

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequence;
    }
    /**
     * Method that creates a melody from an array of pitches and an array of rhythms. TODO Add velocity!
     */
    public static void createMelody(Sequence sequence, int[] key, int timeSig, int numMeasures){
        int totalBeats = timeSig / 10,
                subDivisions = timeSig %10,
                totalTicks = totalBeats * subDivisions * numMeasures,
                tickPlacement = 0;

        Track track = sequence.createTrack();

        while(tickPlacement < totalTicks){
            FibonacciCalculator fibs = new FibonacciCalculator();
            PrimeNumberCalculator primes = new PrimeNumberCalculator();
            int[] fibArray = fibs.getFibArray(10);
            int[] pitches = new int[fibArray.length];
            for (int i = 0; i < fibArray.length; i++) {
                int noteIndex = fibArray[i] % 10;
                pitches[i] = getKey()[noteIndex];
            }
            String primeString = primes.primeString(2, 17);
            String[] rhythmsString = primeString.split(", ");
            int[] rhythmArray = new int[rhythmsString.length];
            for (int i = 0; i < rhythmsString.length; i++) {
                int rhyIndex = Integer.parseInt(rhythmsString[i])%4;
                rhythmArray[i] = getRhythms()[rhyIndex];
            }
            for (int i = 0; i < min(pitches.length, rhythmArray.length); i++) {
                track.add(makeMidiEvent(144, pitches[i], tickPlacement));
                track.add(makeMidiEvent(128, pitches[i], tickPlacement + rhythmArray[i]));
                tickPlacement += rhythmArray[i];
            }
        }
    }

    public static MidiEvent makeMidiEvent(int command, int pitch, int tick){
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage(command, 1, pitch, 64);
            event = new MidiEvent(message, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public static Sequence compose(int[] key, int measuresPerChord) throws InvalidMidiDataException {
        //TODO create an array of sequences to make a song with multiple voices. Expands on createMelody
        Sequence sequence = new Sequence(Sequence.PPQ, 4);
        WriteMidi.createMelody(sequence, key, 44, measuresPerChord);

        return sequence;

    }
    public static void writeMidi(Sequence s, String path) {
        try{
            MidiSystem.write(s, 0, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}
