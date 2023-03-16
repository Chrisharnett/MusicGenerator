package mylearning.assignment2;

/**
 * A class to generate Prime Numbers and to break ints down into products of primes.
 *
 * @author saxDev
 * @studentnumber 20188141
 *
 **/
public class PrimeNumberCalculator {
    public String factorString(int n) {
        String factors = "";
        for (int i = 1; i < n; i++) {
            if(n%i == 0){
                factors += (i + ",");
            }
        }
        factors += n;
        return factors;
    }

    /**
     * Method that returns a string of the prime numbers between a lowerLimit and an upperLimit.
     * @param lowerLimit
     * @param upperLimit
     * @return newPrimes
     */
    public String primeString(int lowerLimit, int upperLimit) {
        String primes = "";
        boolean primeTest = false;
        for (int i = lowerLimit; i < upperLimit; i++) {
            for (int j = 2; j < i; j++) {
                primeTest = false;
                if (i % j == 0) {
                    break;
                }
                primeTest = true;
            }
            if (primeTest == true) {
                primes += (i + ", ");
            }
        }
        String newPrimes;
        if (isPrime(upperLimit)) {
            newPrimes = primes + upperLimit;
        } else {
            newPrimes = primes.substring(0, (primes.length() - 2));
        }
        return newPrimes;
    }
    /**
     * Determine if a number is prime
     * @param n natural number (>0)
     * @return true if prime
     */
    public boolean isPrime(int n) {
        //TODO only loop up to square root.
        if (n > 2){
            for (int j = 2; j < n; j++) {
                if (n % j == 0) {
                return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the unique prime factorization of an int spaced by * . Note a prime will return it's own value
     * @param n natural number(>0)
     * @return string of form p1*p2*......pN
     */
    public String getUniquePrimeFactorization(int n){
        String factors = "";
        for (int i = 2; i <= n; i++) {
            if(n % i == 0 && isPrime(i) == true){
                while (n % i==0){
                    factors += (i + "*");
                    n /= i;
                }
            }
        }
        String factorsClean = factors.substring(0, (factors.length()-1));
        return factorsClean;
    }
}
