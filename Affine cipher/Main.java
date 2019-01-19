package crypto_laba1.crypto_laba3;

import crypto_laba1.*;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static crypto_laba1.EnthropyCalculating.enthropyCalc;
import static crypto_laba1.EnthropyCalculating.frequenctCalc;
import static crypto_laba1.crypto_laba3.NewMain.findingReverseModule;
import static crypto_laba1.crypto_laba3.NewMain.mod;
import static java.lang.Math.pow;


public class Main {
    private static final String file = "I:\\crypto\\test.txt";
    private static final String RUSSIAN_ALPHABET_IN_STRING = "абвгдежзийклмнопрстуфхцчшщыьэюя";
    private static String[] getMostFrequetnBigramsInText = new String[]{"вм", "мь", "кч", "жг", "вц"}; //for test "вм", "мь", "кч", "жг", "вц"};
    //for variant {"лл", "цл", "ул", "ле", "ял"}
    private static String[] mostFrequentBigramsInRussian = new String[] {"ст" , "но", "то", "на", "ен"};
    public static void main(String[] args) throws FileNotFoundException {
        TextReader.checkFile(file);
        String text = TextReader.textReader(file);
        text = text.replaceAll("\n", "");
        System.out.println(text);
        String bigrams = BigramCreator.bigramCreatingWithoutIntersection(text);
        String[] arrayOfBigrams =  TextSpliter.stringInArray(bigrams);
        String[] arrayOfUniqueBigrams = MainCrypto1.gettingUniqueBigramWithoutSpaces(RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        HashMap<String, Integer> hm = EnthropyCalculating.calculatingBigramFrequency(arrayOfUniqueBigrams, arrayOfBigrams);
        List l = new LinkedList(hm.entrySet());
        MainCrypto1.HashMapSorting(l);
        ArrayList<Integer> openTextBigramNumbers = creatingTheNumberForBigrams(mostFrequentBigramsInRussian,RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        ArrayList<Integer> cyptheredTextBigramNumbers = creatingTheNumberForBigrams(getMostFrequetnBigramsInText,RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        ArrayList<ArrayList<Integer>> arrayForPossibleSolutions = creatingArrayForPossibleSolutions(openTextBigramNumbers,cyptheredTextBigramNumbers);
        ArrayList<Integer> arrayOfNumbersForCypheredText = creatingTheNumberForBigrams(arrayOfBigrams, RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        ArrayList<ArrayList<Integer>> arrayOfTexts = new ArrayList<>();
        for (int i = 0; i < arrayForPossibleSolutions.size(); i++) {
            ArrayList<Integer> arrayForPossibleTexts = new ArrayList<>();
            ArrayList<Integer> x0 = arrayForPossibleSolutions.get(i);
            for (int j = 0; j < arrayOfNumbersForCypheredText.size(); j++) {
                int y = arrayOfNumbersForCypheredText.get(j);
                int x = creatingNumbersForOpenText(x0,y,31);
                arrayForPossibleTexts.add(x);
            }
            arrayOfTexts.add(arrayForPossibleTexts);
        }
        ArrayList<Integer> arrayOfNumbersForEachBigram = creatingTheNumberForBigrams(arrayOfUniqueBigrams, RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        HashMap<String, Integer> hashMapForUniqueBigrams = creatingMapForNumbers(arrayOfUniqueBigrams, arrayOfNumbersForEachBigram);
        ArrayList<StringBuilder> arrayOfSb = creatingTextsWithMap(arrayOfTexts,hashMapForUniqueBigrams);
        int i=0;
        int counter = 0;
       while( i != arrayOfSb.size()-1) {
           StringBuilder sb = arrayOfSb.get(i);
           String s = sb.toString();

               if(!arrayOfSb.get(i).equals(arrayOfSb.get(i+1)))
               {

                   ArrayList<Integer> al =   arrayForPossibleSolutions.get(counter);
                   counter++;
                   ArrayList<String> als =splitingTextInBigrams(s);
                   System.out.println(al.toString());
                   System.out.println(als.toString());
               }

           i++;
        }
        arrayOfSb.trimToSize();
        for (int j = 0; j < arrayOfSb.size()-1; j++) {
            String str1 = arrayOfSb.get(j).toString();
            String str2 = arrayOfSb.get(j+1).toString();
            if(!str1.equals(str2))
            {
                double enthr = enthropyCalc(str1,RUSSIAN_ALPHABET_IN_STRING.toCharArray());
                if(enthr > 4 && enthr < 4.7) {
                    System.out.println("Monogram enthropy " + enthr);
                    System.out.println(str1);
                }
            }


        }



    }

    public static int[] ExtendedEuclid(int a, int b)
    {
        if (b == 0)
            return new int[] { a, 1, 0 };

        int[] vals = ExtendedEuclid(b, a % b);
        int d = vals[0];
        int k = vals[2];
        int l = vals[1] - (a / b) * vals[2];
        return new int[] { d, k, l };
    }

    public static ArrayList<Integer> creatingTheNumberForBigrams(String[] bigrams, char[] alphabet)
    {
        ArrayList<Integer> al = new ArrayList();
        int number;
        int a = 0;
        int b = 0;
        for (String tmp:bigrams) {
            for (int i = 0; i <alphabet.length ; i++) {
                if(tmp.charAt(0) == alphabet[i])
                    a = i;
            }
            for (int i = 0; i <alphabet.length ; i++) {
                if(tmp.charAt(1) == alphabet[i])
                    b = i;
            }
            number = a*31 +b;
            al.add(number);
        }


        return al;
    }

    public static ArrayList<Integer> findingFirstVariable(int x1, int y1, int x2, int y2, int n)
    {
        ArrayList<Integer> al = new ArrayList<>();

        int d = gcd(x1-x2,n*n);
        int x;
        if(d==1)
        {

            int vals = findingReverseModule(x1-x2, n*n);
            x = mod(vals * (y1-y2),(n*n));
            al.add(x);
        }
        else
        {
            if((y1-y2)%d != 0)
            {
                System.out.println("No solutions");
                return null;
            }
            else
            {
                int a1 = (x1-x2)/d;
                int b1 = (y1-y2)/d;
                int n1 = (n*n)/d;
                int x0 = findingReverseModule(a1,n1) * b1;

                for (int i = 0; i < d; i++) {
                    al.add(mod((x0 + i*n),(n*n)));
                }
                
            }
        }
        return al;
    }

    public static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static int findingSecondVariable(int x, int y, int a, int n)
    {
        int b = mod((y - a*x) , (n*n));
        return b;
    }

    public static int creatingNumbersForOpenText( ArrayList<Integer> arrayForPossibleSolutions, int y, int n)
    {
        int x=0;
        for (int i = 0; i < arrayForPossibleSolutions.size()-1; i++) {
            int a = arrayForPossibleSolutions.get(i);
            int b = arrayForPossibleSolutions.get(i+1);
            x= mod(findingReverseModule(a,(int) pow(n,2)) * (y - b) , n*n);
            i++;
        }
        return x;
    }

    public static ArrayList<ArrayList<Integer>> creatingArrayForPossibleSolutions(ArrayList<Integer> openTextBigramNumbers, ArrayList<Integer> cyptheredTextBigramNumbers)
    {
        ArrayList<ArrayList<Integer>> arrayOfPossibleAnswers = new ArrayList<>();
        for (int i = 0; i < openTextBigramNumbers.size() -1; i++) {
            int x1 = openTextBigramNumbers.get(i);
            int x2 = openTextBigramNumbers.get(i+1);
            for (int j = 0; j <cyptheredTextBigramNumbers.size() - 1 ; j++) {
                int y1 = cyptheredTextBigramNumbers.get(j);
                int y2 = cyptheredTextBigramNumbers.get(j+1);
                arrayOfPossibleAnswers.add(findingFirstVariable(x1, y1, x2,y2,31));
            }

        }
        for (ArrayList<Integer> arrayOfPossibleAnswer : arrayOfPossibleAnswers) {
            if (arrayOfPossibleAnswer != null) {
                System.out.println(arrayOfPossibleAnswer.toString());
            }


        }
        ArrayList<ArrayList<Integer>> arrayForPossibleSolutions = new ArrayList<>();
        for (int i = 0; i <arrayOfPossibleAnswers.size() ; i++) {
            if (arrayOfPossibleAnswers.get(i) != null) {
                for (int j = 0; j < 5; j++) {
                    int x = openTextBigramNumbers.get(j);
                    int y = cyptheredTextBigramNumbers.get(j);
                    ArrayList<Integer> a0 = arrayOfPossibleAnswers.get(i);
                    for (int k = 0; k < a0.size(); k++) {
                        ArrayList<Integer> al = new ArrayList<>();
                        int a1 = a0.get(k);
                        al.add(a1);
                        al.add(findingSecondVariable(x, y, a1, 31));
                        arrayForPossibleSolutions.add(al);

                    }


                }
            }
        }
        return arrayForPossibleSolutions;
    }
    public static HashMap<String,Integer> creatingMapForNumbers(String[] arrayOfUniqueBigrams, ArrayList<Integer> arrayOfNumbersForEachBigram )
    {
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < arrayOfUniqueBigrams.length; i++) {
            int x = arrayOfNumbersForEachBigram.get(i);
            hm.put(arrayOfUniqueBigrams[i], x);
        }

        return hm;
    }

    public static ArrayList<StringBuilder>  creatingTextsWithMap(ArrayList<ArrayList<Integer>> possibleTexts, HashMap<String,Integer> hm)
    {

        ArrayList<StringBuilder> arOfSb = new ArrayList<>();
        for (int i = 0; i < possibleTexts.size(); i++) {
            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> al = possibleTexts.get(i);
            for (int j = 0; j < al.size(); j++) {
                for (String tmp:hm.keySet()) {
                    int x = hm.get(tmp);
                    if(al.get(j) == x)
                    {
                        sb.append(tmp);
                    }
                }
                arOfSb.add(sb);
            }

        }
        return arOfSb;
    }

    public static  ArrayList<String> splitingTextInBigrams(String s)
    {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < s.length(); i+=2) {

            al.add(Character.toString(s.charAt(i)) + Character.toString(s.charAt(i+1)));
        }
         return al;
    }
}

