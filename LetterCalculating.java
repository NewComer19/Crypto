package crypto_laba1;


public class LetterCalculating extends TextReader {


    public static int calculatingNumberOfLetters(String text, char[] arr) {
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < arr.length; j++) {
                if (text.charAt(i) == arr[j]) {
                    textLength++;
                }

            }
        }
        return textLength;
    }
    public static int calculatingLetter(String text, char letter)
    {
        int letterCounter = 0;
        for (int i = 0; i < text.length(); i++) {
            if(letter == text.charAt(i))
            {
                letterCounter++;
            }

        }
        return letterCounter;
    }
    public static int calculatingBigrams(String[] text)
    {
        int counter=0;
        for (int i = 0; i < text.length; i++) {
            if(!text[i].equals(" "))
            {
                counter++;
            }

        }

        return counter;
    }

}
