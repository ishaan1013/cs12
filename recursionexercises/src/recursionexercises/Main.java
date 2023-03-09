// Ishaan Dey
// March 8 2023
// Recursion exercises 1-7

package recursionexercises;

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 1
    System.out.print("Vampires: ");
    int vamps = Integer.parseInt(br.readLine());
    System.out.println(fangs(vamps));

    // 2
    System.out.print("\nSumDiff: ");
    int sd = Integer.parseInt(br.readLine());
    System.out.printf("%.5f%n", sumDiff(sd));

    // 3
    System.out.print("\nDividend: ");
    int dividend = Integer.parseInt(br.readLine());
    System.out.print("Divisor: ");
    int divisor = Integer.parseInt(br.readLine());
    System.out.println(divide(dividend, divisor));

    // 4
    System.out.print("\nAlphabet: ");
    String s = br.readLine();
    System.out.println(alphabet(s));

    // 5
    System.out.print("\nString to reverse: ");
    String reverseStr = br.readLine();
    System.out.print("Replacement character: ");
    char reverseChar = br.readLine().charAt(0);
    System.out.println(reverse(reverseStr, reverseChar));

    // 6
    System.out.print("\nCommas: ");
    int number = Integer.parseInt(br.readLine());
    System.out.println(commas(number));

    // 7

    int[] arr = {8, 5, 8, 8, -234};
    System.out.println("\n" + largest(arr, Integer.MIN_VALUE, Integer.MIN_VALUE));
  }

  // 1
  // takes in # of vampires, returns # of fangs
  static int fangs(int n) {
    if (n == 0) {
      return 0;
    } else {
      if (n % 2 == 0) {
        return 3 + fangs(n - 1);
      }
      return 2 + fangs(n - 1);
    }
  }

  // 2
  // takes in an integer and returns the sum/products of reciprocals
  static double sumDiff(int n) {
    if (n == 0)
      return -1;
    else if (n == 1 || n == -1) {
      return Math.abs(n);
    } else {
      int newN = n > 0 ? n - 2 : n + 2;
      if (newN == 0) {
        return Math.abs(1.0 / n);
      }
      if (n % 2 == 0 && n > 0 || n % 2 != 0 && n < 0)
        return ((1.0 / n) + sumDiff(newN));
      else {
        return ((1.0 / n) * sumDiff(newN));
      }
    }
  }

  // 3
  // takes in 2 integers to divide and returns the rounded quotient
  static int divide(int dividend, int divisor) {
    if (dividend < divisor) {
      return (int) Math.round((double) dividend / divisor);
    } else {
      return 1 + divide(dividend - divisor, divisor);
    }
  }

  // 4
  // takes in a string and returns the # of non-alphabetical characters
  static int alphabet(String s) {
    if (s.length() == 0) {
      return 0;
    }
    String abc = "abcdefghijklmnopqrstuvwxyz";
    if (abc.indexOf(s.charAt(0)) == -1) {
      return 1 + alphabet(s.substring(1));
    } else {
      return alphabet(s.substring(1));
    }
  }

  // 5
  // takes in a string and a character to put between consecutive characters
  static String reverse(String s, char replace) {
    if (s.length() <= 1) {
      return s;
    }

    char first = s.charAt(s.length() - 1);
    String rest = s.substring(0, s.length() - 1);
    char last = rest.charAt(rest.length() - 1);

    if (first == last) {
      return "" + first + replace + reverse(rest, replace);
    }
    return first + reverse(rest, replace);

  }

  // 6
  // takes in a number and formats it with commas and the pos/neg sign
  static String commas(int n) {

    if (Math.abs(n) < 1000) {
      if (n > 0) return "+" + n;
      return "" + n;
    }

    int last3 = Math.abs(n) % 1000;
    int rest = n / 1000;

    return commas(rest) + "," + last3;
  }

  // 7
  // takes in an array and returns the largest number, added to itself if it repeats
  static int largest(int[] arr, int max, int count) {
    if (arr.length == 0) {
      return count;
    }
    if (arr[0] > max) {
      max = arr[0];
      count = max;
    } else if (arr[0] == max) {
      count += max;

    }
    return largest(Arrays.copyOfRange(arr, 1, arr.length), max, count);
  }
}
