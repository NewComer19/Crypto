package crypto_laba1.crypto_laba2;

import crypto_laba1.TextClearing;
import crypto_laba1.TextReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Encryptor extends Main {

    public static void main(String[] args) throws FileNotFoundException {
        TextReader.checkFile(fileForEncrypting);
        StringBuilder textForEncrypting =  new StringBuilder(TextReader.textReader(fileForEncrypting));
        String text = textForEncrypting.toString();
        String textInLower = text.toLowerCase();
        String textClearedFromSpecialSymbols = TextClearing.textClearingFromSpecialSymbols(textInLower);
        System.out.println(textClearedFromSpecialSymbols);
        StringBuilder textClearedFromSpecialSymbolsInStringBuilder = new StringBuilder(textClearedFromSpecialSymbols);
        HashMap<Character, Integer> mapForAlphabet = creatingHashMapForAlphabet(RUSSIAN_ALPHABER_IN_STRING.toCharArray());
        ArrayList<Integer> indexesOfText = transformindTextInIndexes(textClearedFromSpecialSymbols,mapForAlphabet);
        System.out.println(countingRepeats(textClearedFromSpecialSymbolsInStringBuilder,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));
        int switcher=1;
        switch (switcher)
        {
            case 1:
                ArrayList<Integer> indexesOfEncryptedTextWithLengthTwo = cypherCreating(indexesOfText,"бо",mapForAlphabet);
                StringBuilder encryptedTextWithKeyTwo = textIndexesToLetter(indexesOfEncryptedTextWithLengthTwo,mapForAlphabet);
                System.out.println(countingRepeats(encryptedTextWithKeyTwo,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));


            case 2:
                ArrayList<Integer> indexesOfEncryptedTextWithLengthThree = cypherCreating(indexesOfText,"боб",mapForAlphabet);
                StringBuilder encryptedTextWithKeyThree = textIndexesToLetter(indexesOfEncryptedTextWithLengthThree,mapForAlphabet);
                System.out.println(countingRepeats(encryptedTextWithKeyThree,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));
                ;

            case 3:
                ArrayList<Integer> indexesOfEncryptedTextWithLengthFour = cypherCreating(indexesOfText,"боба",mapForAlphabet);
                StringBuilder encryptedTextWithKeyFour = textIndexesToLetter(indexesOfEncryptedTextWithLengthFour,mapForAlphabet);
                System.out.println(countingRepeats(encryptedTextWithKeyFour,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));


            case 4:
                ArrayList<Integer> indexesOfEncryptedTextWithLengthFive = cypherCreating(indexesOfText,"бобаф",mapForAlphabet);
                StringBuilder encryptedTextWithKeyFive = textIndexesToLetter(indexesOfEncryptedTextWithLengthFive,mapForAlphabet);
                System.out.println(countingRepeats(encryptedTextWithKeyFive,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));


            case 5:
                ArrayList<Integer> indexesOfEncryptedTextWithLengthTen = cypherCreating(indexesOfText,"бобафетлучший",mapForAlphabet);
                StringBuilder encryptedTextWithKeyTen = textIndexesToLetter(indexesOfEncryptedTextWithLengthTen,mapForAlphabet);
                System.out.println(countingRepeats(encryptedTextWithKeyTen,RUSSIAN_ALPHABER_IN_STRING.toCharArray()));
                break;
                default:
                    System.out.println("Invalid enter");
                    break;
        }


    }

    public static HashMap<Character, Integer>  creatingHashMapForAlphabet (char[] arr)
    {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hm.put(arr[i],i);
        }
        return hm;
    }

    public static ArrayList<Integer> transformindTextInIndexes(String text , HashMap<Character, Integer>  hm)
    {
        ArrayList<Integer> al = new ArrayList<>();
        creatingArrayOfIndexes(text, hm, al);
        return al;
    }

    public static ArrayList<Integer> cypherCreating(ArrayList<Integer> al, String key,  HashMap<Character, Integer>  hm)
    {
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<Integer> arrayOfIndexesForKey = creatingArrayOfIndexes(key, hm, tmp);
        arrayOfIndexesForKey.trimToSize();

            for (int i =0; i < al.size() - 4; i++) {
                for (Integer anArrayOfIndexesForKey : arrayOfIndexesForKey) {
                    int shift;
                    shift = (al.get(i) + anArrayOfIndexesForKey) % 31;
                    al.remove(i);
                    al.add(i, shift);
                    i++;
                }
            }



        return al;
    }

    public static ArrayList<Integer> creatingArrayOfIndexes(String key, HashMap<Character, Integer> hm, ArrayList<Integer> al) {
        Main.creatingArrayOfINdexesForDecypher(key, hm, al);

        return al;
    }
    public static StringBuilder textIndexesToLetter(ArrayList<Integer> al,  HashMap<Character, Integer> hm)
    {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < al.size(); i++) {
            for (Map.Entry<Character, Integer> entry:
                    hm.entrySet()) {
                char keyChar = entry.getKey();
                int value = entry.getValue();
                if(al.get(i) == value)
                {
                    sb.append(keyChar);

                }

            }
        }


        sb.trimToSize();
        return sb;
    }

}
