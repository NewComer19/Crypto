package crypto_laba1;

import javax.xml.soap.Text;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Main {
    private static String fileName = "I:\\crypto\\vojna.txt";
    private static String fileNameVojnaWithoutSpaces = "I:\\crypto\\vojna_without_special_symbols.txt";
    private static String clearedFile = "I:\\crypto\\IdiotCleared.txt";
    private static String bigramFile = "I:\\crypto\\bigram.txt";
    private static String bigramFinalFile = "I:\\crypto\\bigram_finak.txt";
    private static final char[] RUSSIAN_ALPHABET_WITH_SPACES = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ы', 'ь', 'э', 'ю', 'я', ' '};
    private static final char[] RUSSIAN_ALPHABET_WITHOUT_SPACES = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ы', 'ь', 'э', 'ю', 'я'};


    public static String getFileName() {
        return fileName;
    }

    public static String getClearedFile() {
        return clearedFile;
    }

    public static String getBigramFile() {
        return bigramFile;
    }

    public static void main(String[] args) throws FileNotFoundException {
        TextReader.checkFile(fileName);
        String clearedText = TextReader.textReader(fileName);
        String idiotClearedFromSpaces = TextClearing.textClearingFromSpecialSymbols(clearedText);
        String srt_vojna = BigramCreator.bigramCreatingWithIntersection(clearedText);
        String srt_vojna2 = BigramCreator.bigramCreatingWithoutIntersection(clearedText);
        String srt_vojna3 = BigramCreator.bigramCreatingWithIntersection(idiotClearedFromSpaces);
        String srt_vojna4 = BigramCreator.bigramCreatingWithoutIntersection(idiotClearedFromSpaces);
        String bigramWithoutIntersectionsWithSpaces = BigramCreator.bigramCreatingWithoutIntersection(clearedText);
        String bigramWithIntersectionsWithSpaces = BigramCreator.bigramCreatingWithoutIntersection(clearedText);
        String[] arrstr = TextSpliter.stringInArray(srt_vojna);
        String[] arrstr2 = TextSpliter.stringInArray(srt_vojna2);
        String[] arrstr3 = TextSpliter.stringInArray(srt_vojna3);
        String[] arrstr4 = TextSpliter.stringInArray(srt_vojna4);
        String[] uniqueBigramsWithSpaces = gettingUniqueBigramWithSpaces(RUSSIAN_ALPHABET_WITH_SPACES);
//        mapForMatrixDisplaying(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigram(RUSSIAN_ALPHABET_WITH_SPACES), arrayOfBigramsWithoutIntersection));
//        mapForMatrixDisplaying(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigram(RUSSIAN_ALPHABET_WITH_SPACES), arrayOfBigramsWithIntersection));
//        List l = new LinkedList(EnthropyCalculating.frequenctCalc(clearedText, RUSSIAN_ALPHABET_WITH_SPACES).entrySet());
//        List l2 = new LinkedList(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigramWithSpaces(RUSSIAN_ALPHABET_WITH_SPACES), arrstr).entrySet());
//        List l3 = new LinkedList(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigramWithSpaces(RUSSIAN_ALPHABET_WITH_SPACES), arrstr2).entrySet());
//        List l4 = new LinkedList(EnthropyCalculating.frequenctCalc(clearedText, RUSSIAN_ALPHABET_WITHOUT_SPACES).entrySet());
//        List l5 = new LinkedList(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigramWithoutSpaces(RUSSIAN_ALPHABET_WITHOUT_SPACES), arrstr).entrySet());
//        List l6 = new LinkedList(EnthropyCalculating.calculatingBigramFrequency(gettingUniqueBigramWithoutSpaces(RUSSIAN_ALPHABET_WITHOUT_SPACES), arrstr2).entrySet());
//        HashMapSorting(l);
//        HashMapSorting(l2);
//        HashMapSorting(l3);
//        HashMapSorting(l4);
//        HashMapSorting(l5);
//        HashMapSorting(l6);
        matrix(clearedText);
        System.out.println("With intersections with spaces");
        matrix(uniqueBigramsWithSpaces, arrstr);
        System.out.println("Without intersections with spaces");
        matrix(uniqueBigramsWithSpaces, arrstr2);
        matrix(idiotClearedFromSpaces);
        System.out.println("With intersections without spaces");
        matrix(uniqueBigramsWithSpaces, arrstr3);
        System.out.println("Without intersections witwithouth spaces");
        matrix(uniqueBigramsWithSpaces, arrstr4);



    }


    public static String[] gettingUniqueBigramWithSpaces(char[] arr) {
        int counter = 0;
        String[] uniqueBigrams = new String[1024];
        if (something(arr, counter, uniqueBigrams)) return uniqueBigrams;

        return null;
    }

    public static boolean something(char[] arr, int counter, String[] uniqueBigrams) {
        try {
            while (counter != uniqueBigrams.length) {
                for (char anArr : arr) {
                    for (char anArr1 : arr) {
                        uniqueBigrams[counter] = Character.toString(anArr) + Character.toString(anArr1);
                        counter++;
                    }


                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] gettingUniqueBigramWithoutSpaces(char[] arr) {
        int counter = 0;
        String[] uniqueBigrams = new String[961];
        if (something(arr, counter, uniqueBigrams)) return uniqueBigrams;

        return null;
    }


    public static void mapForMatrixDisplaying(Object map) {
        System.out.println(map.toString());
        HashMap<String, Integer> hm;
        hm = (HashMap<String, Integer>) map;

    }

    public static void HashMapSorting(List l) {
        Collections.sort(l, (Comparator) (obj1, obj2) -> ((Comparable) ((Map.Entry) (obj1)).getValue

                ()).compareTo(((Map.Entry) (obj2)).getValue()));
        System.out.println(l);
    }

    public static void matrix(String[] bigrams, String[] text)
    {
       HashMap<String, Integer> mp = EnthropyCalculating.calculatingBigramFrequency(bigrams, text);
       double sum = 0;
       double counter = 0;
       double enthropy =0;
        for (Character tmp: RUSSIAN_ALPHABET_WITH_SPACES) {
            for (Character tmp1: RUSSIAN_ALPHABET_WITH_SPACES) {
                sum +=mp.get(Character.toString(tmp) + Character.toString(tmp1));
            }
        }
        for (Character tmp: RUSSIAN_ALPHABET_WITH_SPACES) {
            for (Character tmp1: RUSSIAN_ALPHABET_WITH_SPACES) {
                counter =mp.get(Character.toString(tmp) + Character.toString(tmp1));
                if(counter != 0)
                {
                    enthropy += counter/sum * EnthropyCalculating.logarithm(counter/sum,2);
                }

            }
        }
        System.out.println(enthropy/2);
    }
    public static void matrix(String text)
    {
        HashMap<Character, Double> mp = EnthropyCalculating.frequenctCalc(text, RUSSIAN_ALPHABET_WITH_SPACES);
        double sum = 0;
        double counter = 0;
        double enthropy =0;
        for (Character tmp: RUSSIAN_ALPHABET_WITH_SPACES) {

                sum +=mp.get(tmp);

        }
        for (Character tmp: RUSSIAN_ALPHABET_WITH_SPACES) {

                counter =mp.get(tmp);
                if(counter != 0)
                {
                    enthropy += counter/sum * EnthropyCalculating.logarithm(counter/sum,2);
                }


        }
        System.out.println(enthropy);
    }


}



