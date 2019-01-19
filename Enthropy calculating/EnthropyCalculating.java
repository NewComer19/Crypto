package crypto_laba1;

import java.util.HashMap;

public class EnthropyCalculating {
    public static  HashMap frequenctCalc(String text, char[] arr) {
        HashMap<Character,Double>  hmForSingleLetters = new HashMap<>();
        int numberOfLettersInText = LetterCalculating.calculatingNumberOfLetters(text,arr);
        double enthropy =0;
        TextReader.resultPrint(numberOfLettersInText);

        for (char letter : arr) {
            double letter1 = LetterCalculating.calculatingLetter(text,letter);
            double frequencyOfLetter = letter1/text.length();
            enthropy -= frequencyOfLetter * logarithm(frequencyOfLetter,2);
            hmForSingleLetters.put(letter,letter1);
        }

        System.out.println("Monogram enthropy " + enthropy);
        return hmForSingleLetters;


    }

    public static double enthropyCalc(String text, char[] arr) {
        int numberOfLettersInText = LetterCalculating.calculatingNumberOfLetters(text,arr);
        double enthropy =0;

        for (char letter : arr) {
            double letter1 = LetterCalculating.calculatingLetter(text,letter);
            double frequencyOfLetter = letter1/text.length();
            enthropy -= frequencyOfLetter * logarithm(frequencyOfLetter,2);
        }
        return enthropy;


    }
    public static HashMap calculatingBigramFrequency(String[] bigrams, String[] text)
    {
        HashMap<String,Integer> mapForMatrix = new HashMap<>();
        double counter = text.length;
        double enthropy =0;
        for (String tmp: bigrams) {
            int bigramCounter = 0;
            for (String aText : text) {

                if (tmp.equals(aText)) {
                    bigramCounter++;


                }


            }

            mapForMatrix.put(tmp,bigramCounter);


        }

        return mapForMatrix;
    }

    public static double logarithm(double number, int base)
    {
        return Math.log(number)/Math.log(base);
    }

}
