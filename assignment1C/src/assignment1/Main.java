// Ishaan Dey
// Feb 24 2023
// Assignment 1 Part C: Reads string from file then outputs the largest palindrome, start position (1-indexed), & length

package assignment1;

import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {

    String line = "";
    try {
      BufferedReader inFile = new BufferedReader(new FileReader("input.txt"));
      while ((line = inFile.readLine()) != null) {
        System.out.println("Finding the largest palindrome");
        if (line.length() > 0) {
          int pos = 0, len = 0;
          int n = line.length();

          // iterate through each character and check if char on either side is equal
          // continue doing so until the characters are not equal
          // saves time by not checking substrings where we know a palindrome already exists
          for (int i = 1; i < n; i++) {
            int reach = Math.min(i, n - i - 1);
            for (int x = 1; x <= reach; x++) {
              if (line.charAt(i - x) == line.charAt(i + x)) {
                if (x * 2 + 1 > len) {
                  len = x * 2 + 1;
                  pos = i - x;
                }
              } else {
                break;
              }
            }
          }

          // do the same but for even length palindromes
          for (int i = 1; i < n - 1; i++) {
            if (line.charAt(i + 1) == line.charAt(i)) {

              int reach = Math.min(i, n - i - 2);
              for (int x = 1; x <= reach; x++) {
                if (line.charAt(i - x) == line.charAt((i + 1) + x)) {
                  if (x * 2 + 2 > len) {
                    len = x * 2 + 2;
                    pos = i - x;
                  }
                } else {
                  break;
                }
              }
            }
          }

          System.out.printf("Largest palindrome: %s%n", line.substring(pos, pos + len));
          System.out.printf("Starting position: %d%n", pos + 1);
          System.out.printf("Length: %d%n", len);
        } else {
          System.out.println("Largest palindrome is empty");
          System.out.println("Starting position: 0");
          System.out.println("Length: 0");
        }

      }
      inFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    }

  }
}
