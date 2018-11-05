package crypto_laba1;

public class TextSpliter {
    public static String[] stringInArray(String text)
    {
        return text.split(" ");
    }
    public static void arrayDisplaying(String[] text)
    {
        for (int i = 0; i < text.length -1; i++) {
            System.out.print(text[i] + " , ");
        }
    }
}
