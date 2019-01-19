package CRYPTO_4;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static final String Pole1 = "P1(X) = X23 + X20 + X7 + X6 + X5 + X2 + 1";
    private static final String Pole2 = "P2(X) = X20 + X13 + X12 + X10 + X6 + X5 + X4 + X3 + X2 + X + 1";

    public static void main(String[] args) {
        int polDegree1 = 23;
        int polDegree2 = 20;
        StringBuilder impF1 = impulseFunction(polDegree1);
        StringBuilder impF2 = impulseFunction(polDegree2);
        int impFLength1 = impF1.length();
        int impFLength2 = impF1.length();
        System.out.println(impF1.length() + "\n" + impF2.length());
        StringBuilder recurrenceSequence1 = L1(impF1,(int)(Math.pow(2,polDegree1) - 1),polDegree1);
        StringBuilder recurrenceSequence2 = L2(impF2,(int)(Math.pow(2,polDegree2) - 1),polDegree2);
//        System.out.println("Reccurent 1" + recurrenceSequence1.toString());
//        System.out.println("Reccurent 2" + recurrenceSequence2.toString());

        System.out.println(recurrenceSequence1.length() + "\n" + recurrenceSequence2.length());
        int period1 = period(recurrenceSequence1,impF1,impFLength1);
        int period2 = period(recurrenceSequence2,impF2,impFLength2);
        System.out.println("Processing ...");
        System.out.println(period1);
        System.out.println("Counted");
        System.out.println(period2);
        StringBuilder newRecSeq1 = L1(impF1,period1,polDegree1);
        StringBuilder newRecSeq2 = L2(impF2,period2,polDegree2);
        polynomCharacteristic(Pole1,period1,(int)(Math.pow(2,polDegree1) - 1));
        polynomCharacteristic(Pole2,period2,(int)(Math.pow(2,polDegree2) - 1));
        autoCorel(Pole1,newRecSeq1,10);
        autoCorel(Pole2,newRecSeq2,10);
        distributionOfMonograms(Pole1,newRecSeq1);
        distributionForMultyGrams(newRecSeq1);
        distributionOfMonograms(Pole2,newRecSeq2);
        distributionForMultyGrams(newRecSeq2);
//        try(FileWriter writer = new FileWriter("notes3.txt", false)) {
//            writer.write(impF1 + "\n" + impF2);
//            writer.flush();
//        }
//        catch (Exception e)
//        {
//
//        }

    }

    public static void getArrayOfPossibleNumbers(BigInteger intialValue)
    {
        BigInteger two = new BigInteger("2");
        int bitLength = 25;
        System.out.println(two.toString(2));
        System.out.println(two.testBit(bitLength - 1));
        for (int i = 1; i < 10; i++) {
            two = two.shiftLeft(0);
            boolean c = two.testBit(i)^two.testBit(i+3);
        }

    }

    public static StringBuilder impulseFunction(int polDegree)
    {
        StringBuilder impulseF = new StringBuilder();
        for (int i = 0; i < polDegree-1; i++) {
            impulseF.append(0);
        }
        return impulseF.append(1);
    }

    public static StringBuilder L1(StringBuilder imf, int maxPeriod, int polDegree)
    {
        StringBuilder rs = imf;
        for (int i = 0; i < maxPeriod-polDegree; i++) {
            rs.append(rs.charAt(i+20)^ rs.charAt(i+7)^ rs.charAt(i+6)^ rs.charAt(i+5)^ rs.charAt(i+2)^ rs.charAt(i));
        }

        return rs;
    }

    public static StringBuilder L2(StringBuilder imf, int maxPeriod, int polDegree)
    {
        StringBuilder rs = imf;
        for (int i = 0; i < maxPeriod-polDegree; i++) {
            rs.append(rs.charAt(i+13)^ rs.charAt(i+12)^ rs.charAt(i+10)^ rs.charAt(i+6)^ rs.charAt(i+5)^ rs.charAt(i+4)^rs.charAt(i+3)^rs.charAt(i+2)^rs.charAt(i+1)^rs.charAt(i));
        }

        return rs;
    }
    public static int period(StringBuilder reccurent, StringBuilder impF, int impFLength)
    {
        int period = 1;
         String impFString = impF.toString();
         String recurentInString = reccurent.toString();
        for (int i = 1; i < reccurent.length() - impFLength; i++) {
            if(!recurentInString.substring(i,i+impFLength).equals(impFString))
            {
                period+=1;
            }
            else
            {
                return period;
            }
        }
        return period;
    }

    public static void autoCorel(String polynom, StringBuilder newRs, int period)
    {
        period = 10;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < period+1; i++) {
           String shiftedRs = newRs.substring(newRs.length() - i) + newRs.substring(0,newRs.length() - i);
           hm.put(i,0);
            for (int j = 0; j < newRs.length(); j++) {
                if(newRs.charAt(j) != shiftedRs.charAt(j))
                {
                    hm.put(i,hm.get(i) + 1);
                }
                
            }
        }
        System.out.println("Polynom" + polynom);
        System.out.println("Number of misplaced bits");
        for (int i = 0; i <hm.size() ; i++) {
            System.out.println("d=" + i + " " +(hm.get(i)/2));
        }
    }
    public static void distributionOfMonograms(String polynom, StringBuilder recurenc)
    {
        ArrayList<Character> al = new ArrayList<>();
        al.add('0');
        al.add('1');
        System.out.println("Polynom: " + polynom);

        for (Character temp: al) {
            double counter = 0;
            for (int i = 1; i <recurenc.length(); i++) {
                if(recurenc.charAt(i) == temp)
                    counter++;
            }
            double res = counter/recurenc.length();
            System.out.println("For " + temp + " " + res);
        }

    }
    public static void distributionForMultyGrams(StringBuilder recurenc)
    {
        ArrayList<String> al = new ArrayList<>();
        int inital = 0b0;
        for (int i = 0; i < Math.pow(2,2); i++) {
            String s = String.format("%2s", Integer.toBinaryString(inital + i)).replace(' ', '0');
            al.add(s);
        }
        for (int i = 0; i < Math.pow(2,3); i++) {
            String s = String.format("%3s", Integer.toBinaryString(inital + i)).replace(' ', '0');
            al.add(s);
        }
        for (int i = 0; i <Math.pow(2,4) ; i++) {
            String s = String.format("%4s", Integer.toBinaryString(inital + i)).replace(' ', '0');
            al.add(s);
        }
        for (int i = 0; i <Math.pow(2,5) ; i++) {
            String s = String.format("%5s", Integer.toBinaryString(inital + i)).replace(' ', '0');
            al.add(s);
        }
        for (String temp:al) {
            double counter = 0;
            for (int i = 0; i < recurenc.length() - temp.length(); i+=temp.length()) {
                if(recurenc.substring(i,i+temp.length()).equals(temp))
                    counter++;
            }
            System.out.println("For " + temp + " " + counter/(recurenc.length()/temp.length()));
        }
    }

    public static void polynomCharacteristic(String polynom, int period, int maxPeriod)
    {
        System.out.println("Polynom: " + polynom);
        System.out.println("Period: " + period);
        System.out.println("Max period: " + maxPeriod);

        if(period == maxPeriod)
            System.out.println("Primitive-> irreductible");
        else
        {
            if(maxPeriod%period ==0)
            {
                System.out.println("Non-primitive, possible irreductible, cause maxPeriod devidable by period");
            }
            else
                System.out.println("Non-primitive, deceptive");
        }
    }
}
