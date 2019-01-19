package RSA;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");


    public static BigInteger getTWO() {
        return TWO;
    }

    public static BigInteger getTHREE() {
        return THREE;
    }
/*
*
* Input: n > 2, an odd integer to be tested for primality;
       k, a parameter that determines the accuracy of the test
Output: composite if n is composite, otherwise probably prime
write n − 1 as 2s·d with d odd by factoring powers of 2 from n − 1
LOOP: repeat k times:
   pick a randomly in the range [2, n − 1]
   x ← ad mod n
   if x = 1 or x = n − 1 then do next LOOP
   for r = 1 .. s − 1
      x ← x2 mod n
      if x = 1 then return composite
      if x = n − 1 then do next LOOP
   return composite
 */

    public boolean isPrime(BigInteger n, int iteration)
    {

        if(n.compareTo(ZERO)==0 || n.compareTo(ONE) ==0)
        {
            return false;
        }
        if(n.compareTo(TWO) == 0)
        {
            return true;
        }
        if(n.mod(TWO).equals(0))
        {
            return false;
        }
        int s = 0;
        BigInteger d = n.subtract(ONE); //As long as d is even, it is divided by 2 and s is incremented. After the loop d must be odd and s holds the number of factors 2 in n-1.
        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }
        for (int i = 0; i < iteration; i++) {    //LOOP: repeat k times
            BigInteger a = uniformRandom(TWO, n.subtract(TWO)); //pick random a; 2<a<n-1
            BigInteger x = a.modPow(d, n);  //x = a^d mod n
            if (x.equals(ONE) || x.equals(n.subtract(ONE))) // if x=1 or x = n-1, then do next LOOP
                continue;
            int r = 1;
            for (; r < s; r++) { // for r = 1..s-1
                x = x.modPow(TWO, n);  //x = x ^ 2 mod n
                if (x.equals(ONE))     //if x = 1, return false (composite
                    return false;
                if (x.equals(n.subtract(ONE))) //if x= n-1, look at the next a
                    break;
            }
            if (r == s) // None of the steps made x equal n-1.
                return false; //we've exhausted all of our a values, probably composite
        }
        return true; //probably prime

    }
    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }

    public long mulMod(long a, long b, long mod) // (a * b) mod n
    {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

}
