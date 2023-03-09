package st;

import java.io.*;
import java.util.*;

public class Main {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


  public static void main(String[] args) throws IOException {
//        longestWord();
    s();
  }

  static void s() {
    StringBuilder sb = new StringBuilder("hello");
    sb.ensureCapacity(45);
    System.out.println(sb.capacity());
  }

//  static void longestWord() throws IOException {
//    System.out.print("Enter a sentence: ");
//    String sentence = br.readLine();
//    StringTokenizer st = new StringTokenizer(sentence);
//
//    String longest = "";
//    while (st.hasMoreTokens()) {
//      String s = st.nextToken();
//      if (s.length() > longest.length()) {
//        longest = s;
//      }
//    }
//    System.out.println("The longest word is: " + longest);
//  }
//
//  static void mathExp() throws IOException {
//    System.out.print("Enter an expression: ");
//    String exp = br.readLine();
//    StringTokenizer st = new StringTokenizer(exp, "+-*/", true);
//
//    int len = st.countTokens();
//    double res = Integer.parseInt(st.nextToken());
//    char lastOp = st.nextToken().charAt(0);
//    for (int i = 2; i < len; i++) {
//      if (i % 2 == 0) {
//        switch (lastOp) {
//          case '+':
//            res += Integer.parseInt(st.nextToken());
//            break;
//          case '-':
//            res -= Integer.parseInt(st.nextToken());
//            break;
//          case '*':
//            res *= Integer.parseInt(st.nextToken());
//            break;
//          case '/':
//            res /= Integer.parseInt(st.nextToken());
//            break;
//        }
//      } else {
//        lastOp = st.nextToken().charAt(0);
//      }
//    }
//
//    System.out.println("The result is: " + res);
//
//  }
}
