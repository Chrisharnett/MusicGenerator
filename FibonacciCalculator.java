package mylearning.assignment2;

/**
 * Fibonacci Calculator for generating and checking numbers in the fibonacci sequence
 *
 * @author saxDev
 * @studentnumber 20188141
 *
 * the Fibonacci numbers, commonly denoted Fn, form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is:
 * f_0 = 0, f_1 = 1
 * And
 * F_N = F_N-1 + F_N-2 for all N >= 2
 *
 * The beginning of the sequence is thus: 0, 1, 1, 2, 3, 5, 8, 13, ...
 **/
public class FibonacciCalculator {

    public static final int FIB0 = 0;
    public static final int FIB1 = 1;


    /**
     * Checks to see if the number input is the fib seq.
      * @param n Number >0
     * @return true if n is a fib
     */
    public boolean isFibonacciNumber(int n) {
        if (n == 0 || n == 1) {
            return true;
        } else if (n >= 2) {
            int f0 = FIB0;
            int f1 = FIB1;
            int f2 = f0 + f1;
            while (f2 < n) {
                f2 = f0 + f1;
                f0 = f1;
                f1 = f2;
            }
            return (f2 == n);
        }
        return false;
    }
    /**
     * Get the fibonacci number at the requested sequence position
     * @param sequencePosition pos in the ib sequence (>0)
     *
     * @returnvalue int at given sequencePosition
     */
    public int getFibonacciNumber(int sequencePosition){
        int [] fibArray = getFibArray(sequencePosition);
        return fibArray[sequencePosition-1];
        }

    /**
     * Method to return an array of all the Fibonacci numbers until a given sequenceEndNumber
     * @param sequenceEndNumber
     * @return fibArray
     */
    public int[] getFibArray(int sequenceEndNumber){
        int[] fibArray = new int[sequenceEndNumber];
        fibArray[0] = FIB0;
        fibArray[1] = FIB1;
        int f0 = FIB0;
        int f1 = FIB1;
        int f2 = f0 + f1;
        for (int i=2; i < sequenceEndNumber; i++){
            f2 = f0 + f1;
            f0=f1;
            f1=f2;
            fibArray[i] = f2;
        } return fibArray;
    }

    /**
     * Create a string of Fibonacci numbers up to a given number n
     * @param n
     * @return
     */
    public String fibString(int n){
        if (n > 0) {
            String fibString = "0, 1";
            int f0 = FIB0;
            int f1 = FIB1;
            int limit = 1;
            while (limit <= n) {
                limit = f0 + f1;
                f0 = f1;
                f1 = limit;
                if (limit <= n) {
                    fibString += (", " + limit);
                }
            }
            return fibString;
        }
        return null;
    }
}

