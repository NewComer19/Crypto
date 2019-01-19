package crypto_laba1;

import java.util.ArrayList;
import java.util.Arrays;

public class TextClearing {
    public static String textToLowerCase(String text) {

        String lowerText = text.toLowerCase();

        return lowerText;

    }

    public static String textClearingFromLetters(String text) {

        return  text.replace('ё','е').replace('ъ','ь');

    }

    public static String textClearingFromSpecialSymbols(String text)
    {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < sb.length()-1; i++) {
            for (Character tmp: TextReader.getSpecialSymbols()) {
                if(sb.charAt(i) == tmp)
                {
                    sb.deleteCharAt(i);
                }
            }
        }
        return sb.toString();
    }




}