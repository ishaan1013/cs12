// Ishaan Dey
// Feb 24 2023
// Assignment 1 Part C: reads an encoded url from a file and outputs the decoded string

package assignment1;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String url = "";
        try {
            // read from file
            BufferedReader inFile = new BufferedReader (new FileReader ("input.txt"));
            while ((url = inFile.readLine()) != null) {
                // go through all instances of %
                int index = -1;
                while (url.indexOf('%', index+1) != -1) {
                    index = url.indexOf('%', index+1);
                    url = decode(url, index);
                }
                System.out.println(url);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println ("File does not exist");
        }
    }

    // decodes a url using % and ascii values
    // takes in the url and the index of the %
    // returns the decoded url
    static String decode(String url, int index) {
        String hex = url.substring(index + 1, index + 3);
        int decimal = Integer.parseInt(hex, 16);
        char ch = (char) decimal;
        url = url.substring(0, index) + ch + url.substring(index + 3);
        return url;
    }
}
