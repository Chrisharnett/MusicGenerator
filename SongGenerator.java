package mylearning.assignment2;

import javax.sound.midi.*;

/**
 * @author saxDev
 * @studentnumber 20188141
 * User sets an upper limit of n  fibonacci numbers
 * Each fibonacci number impacts a random variable (note, length, etc)
 * Cycles through set of n fibonacci numbers.
 **/
public class SongGenerator {
    int bpm = 120;
    int timeSig = 44;
    int subdivisions = timeSig % 10;
    int numMeasures = 8;

    FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();

    public static Sequence play(int instrument1, int instrument2, int bpm) {
        int timeSig = 44;
        int subDivisions = timeSig % 10;
        int numMeasures = 8; //this is the number of measures that each chord plays over (i.e. the length)
        Sequence sequence = null;
        try {
            sequence = new Sequence(Sequence.PPQ, subDivisions);
        } catch (InvalidMidiDataException e1) {
            e1.printStackTrace();
        }

        try {

            MidiChannel[] midChannel;
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            midChannel = synthesizer.getChannels();
            midChannel[0].programChange(instrument1);
            midChannel[1].programChange(instrument2);
            midChannel = synthesizer.getChannels();

            Sequencer sqr = MidiSystem.getSequencer();
            Transmitter sqrTrans = sqr.getTransmitter();
            Receiver synthesizerReceiver = synthesizer.getReceiver();
            sqrTrans.setReceiver(synthesizerReceiver);

            sqr.open();
//            sequence = WriteMidi.createMelody((WriteMidi.getKey(),  numMeasures));
            sqr.setSequence(sequence);
            sqr.setTempoInBPM(bpm);
            sqr.start();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        return sequence;
    }

    public static MidiEvent makeMidiEvent(int command, int channel, int pitch, int velocity, int tick){
        MidiEvent event = null;
        try {
            ShortMessage message = new ShortMessage(command, channel, pitch, velocity);
            event = new MidiEvent(message, tick);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return event;
    }
}

