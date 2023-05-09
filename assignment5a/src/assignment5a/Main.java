package assignment5a;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException  {
        String line = "";
        ArrayList<String> strings = new ArrayList<String>();
        try {
            BufferedReader inFile = new BufferedReader (new FileReader ("input.txt"));
            System.out.println("Finding the number of Substrings");

            int numLines = Integer.parseInt (inFile.readLine ());

            for (int i = 0; i < numLines; i++) {
                line = inFile.readLine ();
                strings.add (line);
                System.out.println("String: " + line);

                Set<String> set = new HashSet<String>();
                for (int j = 0; j < line.length(); j++) {
                    for (int k = j ; k <= line.length(); k++) {
                        set.add(line.substring(j, k));
                    }
                }

                System.out.println("Number of substrings: " + set.size());
            }
            inFile.close ();



        }
        catch (FileNotFoundException e) {
            System.out.println ("File does not exist");
        }
    }
}
