package assignment1;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String url = "";
        try {
            BufferedReader inFile = new BufferedReader (new FileReader ("input.txt"));
            while ((url = inFile.readLine()) != null) {
                while (url.indexOf('%') != -1) {
                    int index = url.indexOf('%');
                    String hex = url.substring(index + 1, index + 3);
                    int decimal = Integer.parseInt(hex, 16);
                    char ch = (char) decimal;
                    url = url.substring(0, index) + ch + url.substring(index + 3);
                }
                System.out.println(url);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println ("File does not exist");
        }
    }
}
