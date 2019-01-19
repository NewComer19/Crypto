package crypto_laba1;


public class BigramCreator {
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sbWithoutIntersections = new StringBuilder();
    public static String bigramCreatingWithIntersection(String text)
    {

        for (int i = 0; i < text.length()-1; i++) {
            sb.append(text.charAt(i)).append(text.charAt(i+1)).append(' ');
        }
        return sb.toString();
    }

    public static String bigramCreatingWithoutIntersection(String text)
    {

        for (int i = 0; i < text.length()-1; i+=2) {
            sbWithoutIntersections.append(text.charAt(i)).append(text.charAt(i+1)).append(' ');
        }
        return sbWithoutIntersections.toString();
    }

    public static String[] uniqueBigramCreating(char[] arr)
    {
        String[] strArr = new String[1090];
        for (int k = 0; k < strArr.length; k++) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    strArr[k] = String.valueOf(arr[i] + arr[j]);
                }
            }

        }


        return strArr;
    }
}
