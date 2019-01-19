package RSA;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static RSA.MillerRabin.getTWO;

public class Main {
    private static final int BIT_LENGTH = 256; //the key will be two times larger


    public static void main(String[] args) {
        BigInteger p = generatingRandomBigInteger(BIT_LENGTH,1000);
        BigInteger q = generatingRandomBigInteger(BIT_LENGTH,1000);
        BigInteger p1 = generatingRandomBigInteger(BIT_LENGTH,1000);
        BigInteger q1 = generatingRandomBigInteger(BIT_LENGTH,1000);

        while (true)
        {

            if(p.multiply(q).compareTo(p1.multiply(q1)) <= 0)
            {
                break;
            }
            else
            {
                p =  generatingRandomBigInteger(BIT_LENGTH,100);
                q =  generatingRandomBigInteger(BIT_LENGTH,100);
                p1 =  generatingRandomBigInteger(BIT_LENGTH,100);
                q1 =  generatingRandomBigInteger(BIT_LENGTH,100);
            }

        }
        System.out.println(p.toString());
        System.out.println(q.toString());
        System.out.println(p1.toString());
        System.out.println(q1.toString());
        ArrayList<ArrayList<BigInteger>> keyPairOfA = generatingKeyPairs(p,q);
        System.out.println("Keypair for A \n" + keyPairOfA.toString());
        BigInteger d_A = keyPairOfA.get(1).get(0);
        BigInteger n_A = keyPairOfA.get(0).get(0);
        BigInteger e_A = keyPairOfA.get(0).get(1);
        ArrayList<ArrayList<BigInteger>> keyPairOfB = generatingKeyPairs(p1,q1);
        System.out.println("Keypair for B \n" + keyPairOfA.toString());
        BigInteger d_B = keyPairOfA.get(1).get(0);
        BigInteger n_B = keyPairOfA.get(0).get(0);
        BigInteger e_B = keyPairOfA.get(0).get(1);
        BigInteger message = new BigInteger("5673");
        BigInteger s_A = signMessage(message,d_A,n_A);
        System.out.println("Message " + message.toString());
        BigInteger encryptedMessage = encrypt(message,e_A,n_A);
        System.out.println("Encrypted message " + encryptedMessage.toString());
        System.out.println("Decrypted message " + decrypt(encryptedMessage,d_A,n_A));
        System.out.println("Verified message " + verifyMessage(message,s_A,e_A,n_A));
        BigInteger[] anonymShare = sendKey(n_A,d_A,e_B,n_B);
        receiveKey(anonymShare,d_B,n_B,e_A,n_A);

        System.out.println("Test");
        BigInteger testModulus = new BigInteger("BD21B5E8057D427DCCEF668E8EDA62A11858F7B79AB8985D58DA4FB848B414527A4EDCA6BF79C197C510C254C2A97CAAED1DFFB45F85931928DFDA285ACB2B8F",16);
        BigInteger publicExponent = new BigInteger("10001",16);
        BigInteger[] testbi = sendKey(n_A,d_A,publicExponent,testModulus);
        System.out.println("Signature " + testbi[0].toString(16) + " \n" + "Key " + testbi[1].toString(16));

}

    public static BigInteger generatingRandomBigInteger(int bitLength, int cerntainty)
    {
        Random rand = new Random();
        BigInteger bi = new BigInteger(bitLength,rand).setBit(0).setBit(bitLength - 1);

        MillerRabin mr = new MillerRabin();
        while(!mr.isPrime(bi,cerntainty))
        {
            bi = bi.add(getTWO());
        }
        System.out.println(bi.isProbablePrime(1000));
        return bi;

    }

    public static ArrayList<ArrayList<BigInteger>> generatingKeyPairs(BigInteger p, BigInteger q)
    {
        ArrayList<ArrayList<BigInteger>> al = new ArrayList<>();
        ArrayList<BigInteger> pubKey = new ArrayList<>();
        ArrayList<BigInteger> privateKey = new ArrayList<>();
        BigInteger n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE))); //(p-1)*(q-1)
        BigInteger e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        BigInteger d = e.modInverse(m);
        pubKey.add(n);
        pubKey.add(e);
        privateKey.add(d);
        privateKey.add(p);
        privateKey.add(q);
        al.add(pubKey);
        al.add(privateKey);
        return al;

    }
    //generator of BigIntefer with limits
    public static BigInteger generateBigIntegerBetween(BigInteger min, BigInteger max)
    {
        BigInteger bigInteger1 = max.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = max.bitLength();

        BigInteger aRandomBigInt;

        aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
        if (aRandomBigInt.compareTo(min) < 0)
            aRandomBigInt = aRandomBigInt.add(min);
        if (aRandomBigInt.compareTo(max) >= 0)
            aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);

        return aRandomBigInt;
    }
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n)
    {
        return message.modPow(e,n);
    }

    public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n)
    {
        return message.modPow(d,n);
    }
    public static BigInteger signMessage(BigInteger message, BigInteger d, BigInteger n)
    {
        return message.modPow(d,n);
    }

    public static boolean verifyMessage(BigInteger message, BigInteger s, BigInteger e, BigInteger n)
    {
        return message.equals(s.modPow(e, n));
    }

    public static BigInteger[] sendKey(BigInteger n_A, BigInteger d_A, BigInteger e_B, BigInteger n_B)
    {
        BigInteger[] bi = new BigInteger[2];
        BigInteger k = generateBigIntegerBetween(BigInteger.ZERO, n_A);
        System.out.println("Open message " + k.toString());
        BigInteger s = signMessage(k,d_A,n_A);
        BigInteger s1 = encrypt(s,e_B,n_B);
        BigInteger k1 = encrypt(s1,e_B,n_B);
        bi[0] = s1;
        bi[1] = k1;
        return bi;
    }
    public static BigInteger receiveKey(BigInteger[] s1_k1, BigInteger d_B, BigInteger n_B, BigInteger e_A, BigInteger n_A)
    {
        BigInteger s1 = s1_k1[0];
        BigInteger k1 = s1_k1[1];
        BigInteger k = decrypt(k1,d_B,n_B);
        BigInteger s = decrypt(s1,d_B,n_B);
        System.out.println("Decrypted message " + k);
        System.out.println("Check if the right sender " + verifyMessage(k,s,e_A,n_A));
        return k;
    }
}
