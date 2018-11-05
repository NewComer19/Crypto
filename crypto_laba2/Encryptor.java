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
        StringBuilder textClearedFromSpecialSymbolsInStringBuilder = new StringBuilder(textClearedFromSpecialSymbols);
        HashMap<Character, Integer> mapForAlphabet = creatingHashMapForAlphabet(RUSSIAN_ALPHABET_WITHOUT_SPACES);
        ArrayList<Integer> indexesOfText = transformindTextInIndexes(textClearedFromSpecialSymbols,mapForAlphabet);
        System.out.println(countingRepeats(textClearedFromSpecialSymbolsInStringBuilder,RUSSIAN_ALPHABET_WITHOUT_SPACES));
//        ArrayList<Integer> indexesOfEncryptedTextWithLengthTwo = cypherCreating(indexesOfText,"бо",mapForAlphabet);
        ArrayList<Integer> indexesOfEncryptedTextWithLengthThree = cypherCreating(indexesOfText,"боб",mapForAlphabet);
        ArrayList<Integer> indexesOfEncryptedTextWithLengthFour = cypherCreating(indexesOfText,"боба",mapForAlphabet);
        ArrayList<Integer> indexesOfEncryptedTextWithLengthFive = cypherCreating(indexesOfText,"бобаф",mapForAlphabet);
        ArrayList<Integer> indexesOfEncryptedTextWithLengthTen = cypherCreating(indexesOfText,"бобафетлучший",mapForAlphabet);
//        StringBuilder encryptedTextWithKeyTwo = textIndexesToLetter(indexesOfEncryptedTextWithLengthTwo,mapForAlphabet);
//        System.out.println(countingRepeats(encryptedTextWithKeyTwo,RUSSIAN_ALPHABET_WITHOUT_SPACES));
        StringBuilder encryptedTextWithKeyThree = textIndexesToLetter(indexesOfEncryptedTextWithLengthThree,mapForAlphabet);
        System.out.println(countingRepeats(encryptedTextWithKeyThree,RUSSIAN_ALPHABET_WITHOUT_SPACES));
        StringBuilder encryptedTextWithKeyFour = textIndexesToLetter(indexesOfEncryptedTextWithLengthFour,mapForAlphabet);
        StringBuilder encryptedTextWithKeyFive = textIndexesToLetter(indexesOfEncryptedTextWithLengthFive,mapForAlphabet);
        StringBuilder encryptedTextWithKeyTen = textIndexesToLetter(indexesOfEncryptedTextWithLengthTen,mapForAlphabet);

        System.out.println(countingRepeats(encryptedTextWithKeyFour,RUSSIAN_ALPHABET_WITHOUT_SPACES));
        System.out.println(countingRepeats(encryptedTextWithKeyFive,RUSSIAN_ALPHABET_WITHOUT_SPACES));
        System.out.println(countingRepeats(encryptedTextWithKeyTen,RUSSIAN_ALPHABET_WITHOUT_SPACES));
//


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
        for (int i = 0; i < key.length(); i++) {
            for (Map.Entry<Character, Integer> entry:
                    hm.entrySet()) {
                char keyChar = entry.getKey();
                int value = entry.getValue();
                if(key.charAt(i) == keyChar)
                {
                    al.add(value);
                }

            }
        }

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
