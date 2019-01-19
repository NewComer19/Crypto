package crypto_laba1.crypto_laba3;

import crypto_laba1.BigramCreator;
import crypto_laba1.MainCrypto1;
import crypto_laba1.TextReader;
import crypto_laba1.TextSpliter;
import java.math.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static crypto_laba1.crypto_laba3.Main.*;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class NewMain {
    private static final String file = "I:\\crypto\\07.txt";
    private static final String RUSSIAN_ALPHABET_IN_STRING = "абвгдежзийклмнопрстуфхцчшщыьэюя";
    private static String[] mostFrequentBigramsInRussian = new String[] {"ст" , "но", "то", "на", "ен"};
    private static String[] mostFrequetnBigramsInText = new String[] {"вм", "мь", "кч", "жг", "йв"}; //can be changed

    public static void main(String[] args) throws FileNotFoundException {
        TextReader.checkFile(file);
        String text = TextReader.textReader(file);
        System.out.println(text);
        text = text.replaceAll("\n", "");
        String bigrams = BigramCreator.bigramCreatingWithoutIntersection(text);
        String[] arrayOfBigrams =  TextSpliter.stringInArray(bigrams);
        String[] arrayOfUniqueBigrams = MainCrypto1.gettingUniqueBigramWithoutSpaces(RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        System.out.println(Arrays.toString(arrayOfBigrams));
        ArrayList<Integer> cyptheredTextBigramNumbers = creatingTheNumberForBigrams(arrayOfBigrams,RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        System.out.println(cyptheredTextBigramNumbers.toString());
        ArrayList<Integer> openTextNumbers = new ArrayList<>();
        for (int i = 0; i <cyptheredTextBigramNumbers.size() ; i++) {
            openTextNumbers.add(creatingOpenText(35,219,cyptheredTextBigramNumbers.get(i),31));
        }
        System.out.println(openTextNumbers.toString());
        ArrayList<Integer> arrayOfNumbersForEachBigram = creatingTheNumberForBigrams(arrayOfUniqueBigrams, RUSSIAN_ALPHABET_IN_STRING.toCharArray());
        HashMap<String, Integer> hashMapForUniqueBigrams = creatingMapForNumbers(arrayOfUniqueBigrams, arrayOfNumbersForEachBigram);
        creatingOpenText(hashMapForUniqueBigrams,openTextNumbers);
    }

    public static int creatingOpenText(int a, int b, int y, int n)
    {
        int g;
        int x = findingReverseModule(a,(int) pow(n,2));
        g = mod(x * (y - b), (int) pow(n, 2));
        return g;
    }

    public static int mod(int x, int y)
    {
        int result = x % y;
        if (result < 0)
        {
            result += y;
        }
        return result;
    }

    public static void creatingOpenText(HashMap<String, Integer> hashMapForUniqueBigrams,  ArrayList<Integer> openTextNumbers )
    {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < openTextNumbers.size(); i++) {
            for(String tmp: hashMapForUniqueBigrams.keySet())
            {
                int x = hashMapForUniqueBigrams.get(tmp);
                if(openTextNumbers.get(i) == x)
                {
                    al.add(tmp);
                }
            }
        }
        System.out.println(al.toString());
    }

    public static int findingReverseModule(int a, int b)
    {

        if(gcd(a,b) == 1) {

            return mod(ExtendedEuclid(a, b)[1],b);
        }

        return 1;


    }
}
