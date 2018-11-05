package crypto_laba1;
import javax.xml.soap.Text;
import java.io.*;

import java.io.FileNotFoundException;

public class TextReader {

    protected static final char[] SPECIAL_SYMBOLS = {'!','?','-',':',';','—','«','»',',','.','*','(',')','…','–','[',']',' ','*'};
    protected static int textLength=0;


    public static char[] getSpecialSymbols() {
        return SPECIAL_SYMBOLS;
    }
    public static String textReader(final String fileName) throws FileNotFoundException
    {

        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        String s;

        try
        {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();

    }
    public static void checkFile(final String filename) {
        final File file = new File(filename);
        System.out.println(filename + ":");
        if (file.exists()) {
            System.out.println("Файл существует.");
        } else {
            System.out.println("Файл не существует.");
        }
    }
    public static void textPrint(String text)
    {
        System.out.println(text);

    }
    public static void resultPrint(int res)
    {
        System.out.println(res);
    }


}
