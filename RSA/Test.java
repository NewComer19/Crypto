package RSA;

import java.math.BigInteger;
import java.util.ArrayList;

import static RSA.Main.decrypt;
import static RSA.Main.encrypt;
import static RSA.Main.generatingKeyPairs;

public class Test {
    public static void main(String[] args) {
   StringBuilder test = new StringBuilder("000001");
//        System.out.println(test.charAt(0)^test.charAt(2));
        int b = 0b0;

        for (int i = 0; i < Math.pow(2,2); i++) {
            String s = String.format("%2s",Integer.toBinaryString(b + i)).replace(' ', '0');
            System.out.println(s + " " + s.length());
        }
        for (int i = 0; i < Math.pow(2,3); i++) {
            String s = String.format("%3s",Integer.toBinaryString(b + i)).replace(' ', '0');
            System.out.println(s + " " + s.length());
        }
    }
}
