// Ishaan Dey
// Feb 24 2023
// Assignment 1 Part A: takes inputs for buying things at a toy store and creates a receipt in a file

package assignment1;

import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter receipt = new PrintWriter(new FileWriter("receipt.txt"));
    receipt.println("Here is a summary of your purchases:\n");
    receipt.printf("%-32s%-11s%-11s%-11s%n", "ITEM", "QUANTITY", "UNIT COST", "TOTAL COST");
    receipt.printf("%-32s%-11s%-11s%-11s%n", "----", "--------", "---------", "----------");

    int item = 1;
    double finalCost = 0;

    // keeps track of the cheapest item cost (incl. qty)
    double cheap = Integer.MAX_VALUE;
    String cheapStr = "";

    System.out.println("The WONG Toy Store");

    // while loop to keep asking for more items
    while (item != -1) {
      // use methods to read toy name, qty, and cost
      String toyName = getItem(br, item);
      int qty = getQty(br);
      double cost = getCost(br);

      double total = printTotal(qty, toyName, cost, receipt);
      finalCost += total;
      // see if this is the cheapest item
      cheap = Math.min(cheap, total);
      if (cheap == total) {
        cheapStr = String.format("The cheapest toy bought is: %d %s for $%.2f!", qty, toyName, total);
      }

      // see if user wants to buy more toys
      String yn = "";
      while (!yn.equalsIgnoreCase("y") && !yn.equalsIgnoreCase("n")) {
        System.out.print("Are you buying any more toys (y/n): ");
        yn = br.readLine();
      }
      if (yn.equalsIgnoreCase("y")) {
        item++;
      } else {
        // -1 to stop the while loop
        item = -1;
      }
    }

    // print final cost and cheapest item
    receipt.println("----------------------------------------------------------------");
    receipt.printf("%-54s$%.2f%n%n", "FINAL COST", finalCost);
    receipt.println(cheapStr);
    receipt.close();

    System.out.println("\nThank you for shopping at The WONG Toy Store!");
    System.out.println("(Your receipt is saved in receipt.txt)");

  }

  // reads the name of the toy
  // takes in buffered reader to output prompt and int item to print the item number
  // returns input result (string)
  static String getItem(BufferedReader br, int item) throws IOException {
    System.out.printf("\nPlease enter the name of toy #%d: ", item);
    return br.readLine().trim();
  }

  // reads the quantity of the toy
  // takes in buffered reader to output prompt
  // returns input result (int)
  static int getQty(BufferedReader br) throws IOException {
    while (true) {
      try {
        System.out.print("How many of this toy are you buying?: ");
        return Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid integer. Try again.");
      }
    }
  }

  // reads the cost of the toy
  // takes in buffered reader to output prompt
  // returns input result (double)
  static double getCost(BufferedReader br) throws IOException {
    while (true) {
      try {
        System.out.print("Please enter the cost of this toy: $");
        return Double.parseDouble(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid cost. Try again.");
      }
    }
  }

  // calculates the total cost of an item and prints it both in output and in the receipt
  // takes in qty, toyName, cost, and PrintWriter receipt
  // returns total cost (double) to use it calculating the cheapest item
  static double printTotal(int qty, String toyName, double cost, PrintWriter receipt) {
    double total = (Math.round(cost * 100) / 100.0) * qty;
    System.out.printf("The cost for %d %s @ $%.2f each is: $%.2f%n", qty, toyName, cost, total);
    receipt.printf("%-32s%-11d$%-10.2f$%-10.2f%n", toyName, qty, cost, total);
    return total;
  }

}
