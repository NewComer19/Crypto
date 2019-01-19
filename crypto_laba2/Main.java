package crypto_laba1.crypto_laba2;

import crypto_laba1.TextReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main
{
    protected static String fileName = "I:\\crypto\\1.txt";
    protected static final String RUSSIAN_ALPHABER_IN_STRING = "абвгдежзийклмнопрстуфхцчшьыэюя";
    protected static String fileForEncrypting = "I:\\crypto\\textForEncrypting.txt";

    public static String getFileName() {
        return fileName;
    }

    public static String getRussianAlphabetWithoutSpaces() {
        return RUSSIAN_ALPHABER_IN_STRING;
    }

    public static void main(String[] args) throws FileNotFoundException {
        TextReader.checkFile(fileName);
        StringBuilder initialText = new StringBuilder(TextReader.textReader(fileName));
        StringBuilder textWithoutSpaces = removingFromSpaces(initialText);
        System.out.println(textWithoutSpaces.toString());
//        for (int i = 1; i < 150 ; i++) {
//            System.out.println(countingRepeats(creatingNewString(textWithoutSpaces, i),RUSSIAN_ALPHABET_WITHOUT_SPACES) + " " + i);
//        }


//        char[] wat = stringToChar(textWithoutSpaces);
//        printingArray(wat);
        ArrayList<ArrayList> arrayOfArraysOfEverySpaceLetter = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayOfArraysOfEverySpaceLetter.add(everyLetter(i,textWithoutSpaces, 15));
        }
        for (int i = 0; i < arrayOfArraysOfEverySpaceLetter.size(); i++) {
            ArrayList<Character> al = arrayOfArraysOfEverySpaceLetter.get(i);
            HashMap<Character, Integer> alOfHm = new HashMap<>();
            for (int j = 0; j < RUSSIAN_ALPHABER_IN_STRING.toCharArray().length; j++) {
                indexOfIncluding(al,RUSSIAN_ALPHABER_IN_STRING.toCharArray()[j],alOfHm);
            }
//            System.out.println(alOfHm.entrySet());
//            System.out.println("Next");
//            alOfHm.entrySet().stream()
//                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
//                    .forEach(System.out::println);
        }

//        LinkedList<ArrayList> arrayOfFrequency = new LinkedList<>();
//        for (int i = 0; i < arrayOfArraysOfEverySpaceLetter.size(); i++) {
//            ArrayList alNew = arrayOfArraysOfEverySpaceLetter.get(i);
//
//            for (int j = 0; j < RUSSIAN_ALPHABET_WITHOUT_SPACES.length; j++) {
//                indexOfIncluding(alNew,RUSSIAN_ALPHABET_WITHOUT_SPACES[j]);
//            }
//            System.out.println(indexOfRepeating(alNew) + " " + i);
//            System.out.println("Next");
//        }


    }
    public static StringBuilder creatingNewString(StringBuilder string, int everyNLetter)
    {

        StringBuilder sb = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i+=everyNLetter) {

            char tmp = string.charAt(i) ;
            sb.append(tmp);
        }
        sb.trimToSize();
        return sb;
    }
    public static double countingRepeats(StringBuilder string, char[] alphabet)
    {
        string.trimToSize();
        double sum=0;
        for (int i = 0; i <alphabet.length ; i++) {
            double repeats = indexOfIncluding(string, alphabet[i]);
            sum += repeats * (repeats - 1)/(string.length() *(string.length() - 1));
        }

        return sum;
    }
    public static int indexOfIncluding(StringBuilder sb, char letter)
        {

            sb.trimToSize();
        int counter = 0;
        for (int i = 0; i <sb.length() ; i++) {
           if(letter ==  sb.charAt(i))
           {
               counter++;
           }
        }
        return counter;
    }
    public static StringBuilder removingFromSpaces( StringBuilder string)
    {
        string.trimToSize();
        for (int i = 0; i <string.length() ; i++) {
            if(string.charAt(i) == ' ')
            {
                string.deleteCharAt(i);
            }

        }
        return string;
    }
    public static ArrayList<Integer> decypherCreating(ArrayList<Integer> al, String key,  HashMap<Character, Integer>  hm) {
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<Integer> arrayOfIndexesForKey = creatingArrayOfIndexes(key, hm, tmp);
        arrayOfIndexesForKey.trimToSize();

        for (int i = 0; i < al.size() - 4; i++) {
            for (Integer anArrayOfIndexesForKey : arrayOfIndexesForKey) {
                int shift;
                shift = (al.get(i) - anArrayOfIndexesForKey) % 31;
                al.remove(i);
                al.add(i, shift);
                i++;
            }
        }


        return al;
    }
    public static ArrayList<Integer> creatingArrayOfIndexes(String key, HashMap<Character, Integer> hm, ArrayList<Integer> al) {
        creatingArrayOfINdexesForDecypher(key, hm, al);

        return al;
    }

    public static void creatingArrayOfINdexesForDecypher(String key, HashMap<Character, Integer> hm, ArrayList<Integer> al) {
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
    }

        public static char[] stringToChar(String text)
    {

        return text.toCharArray();
    }

    public static void printingArray(char[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static ArrayList indexes(char[] arr)
    {
        ArrayList<Integer> idexesOfThis = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < RUSSIAN_ALPHABER_IN_STRING.toCharArray().length; j++) {
                if(arr[i] == RUSSIAN_ALPHABER_IN_STRING.toCharArray()[j])
                {
                    idexesOfThis.add(j);
                }
            }
        }
        return idexesOfThis;
    }
    public static ArrayList countingOfRepeats(ArrayList arr)
    {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {

            if(arr.get(i).equals(15) && arr.get(i + 1).equals(0))
            {
                al.add(i);
                i++;
            }
        }
        System.out.println(al.toString());
        return al;
    }

    public static void countingIntervals(ArrayList arr)
    {
        int interval = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            interval = (int)arr.get(i+1) - (int)arr.get(i);
            System.out.println(interval);
        }
    }

    public static HashMap indexOfIncluding(ArrayList arr, char letter, HashMap<Character, Integer> hm)
    {
        int counter = 0;

        for (int i = 0; i <arr.size() ; i++) {
           if(letter == (char)arr.get(i))
           {
               counter++;
           }
        }
        hm.put(letter, counter);
        System.out.println(letter + " " + counter);
        return hm;
    }
//    public static double indexOfRepeating(ArrayList arr)
//    {
//        double sum=0;
//        for (int i = 0; i <RUSSIAN_ALPHABET_WITHOUT_SPACES.length ; i++) {
//            double index = indexOfIncluding(arr,RUSSIAN_ALPHABET_WITHOUT_SPACES[i]);
//            sum += index * (index - 1)/(arr.size() *(arr.size() - 1));
//        }
//
//        return sum;
//    }
    public static ArrayList everyLetter(int firstLetter ,StringBuilder str, int indexOfRepeatedLetter)
    {
        ArrayList al = new ArrayList();
        for (int i = firstLetter; i < str.length(); i+=indexOfRepeatedLetter) {
                al.add(str.charAt(i));

        }
        al.trimToSize();
        return al;
    }
}
