package assignment5b;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxD;
        String[] lowerInput, upperInput;
        double lower, upper;
        while (true) {
            try {
                System.out.print("Enter the maximum denominator: ");
                maxD = Integer.parseInt(br.readLine());
                if (maxD < 1) {
                    System.out.println("Denominator must be greater than 0. Try again.\n");
                    continue;
                }
                System.out.print("Enter the lower limit: ");
                lowerInput = br.readLine().split("/");
                if (lowerInput.length != 2) {
                    System.out.println("Please enter valid numbers/fractions. Try again.\n");
                    continue;
                }
                lower = Double.parseDouble(lowerInput[0]) / Double.parseDouble(lowerInput[1]);
                if (Double.parseDouble(lowerInput[1]) < 1) {
                    System.out.println("Denominator must be greater than 0. Try again.\n");
                    continue;
                }
                if (lower < 0 || lower > 1) {
                    System.out.println("Only values between 0 and 1 inclusive.\n");
                    continue;
                }
                System.out.print("Enter the upper limit: ");
                upperInput = br.readLine().split("/");
                if (upperInput.length != 2) {
                    System.out.println("Please enter valid numbers/fractions. Try again.\n");
                    continue;
                }
                upper = Double.parseDouble(upperInput[0]) / Double.parseDouble(upperInput[1]);
                if (Double.parseDouble(upperInput[1]) < 1) {
                    System.out.println("Denominator must be greater than 0. Try again.\n");
                    continue;
                }
                if (upper < 0 || upper > 1) {
                    System.out.println("Only values between 0 and 1 inclusive.\n");
                    continue;
                }
                if (upper <= lower) {
                    System.out.println("Upper limit has to be HIGHER than the lower limit. Try again.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numbers/fractions. Try again.\n");
            }
        }

        Set<Double> all = new HashSet<Double>();
        Set<Double> inRange = new HashSet<Double>();

        for (int i = 1; i <= maxD; i++) {
            for (int j = 0; j <= i; j++) {
                Fraction f = new Fraction(j, i);
                all.add(f.getValue());

                if (f.getValue() >= lower && f.getValue() <= upper) {
                    inRange.add(f.getValue());
                }
            }
        }

        System.out.println("Total number of fractions: " + all.size());
        System.out.printf("Number of Fractions between %s/%s and %s/%s inclusive: %d\n", lowerInput[0], lowerInput[1], upperInput[0], upperInput[1], inRange.size());


    }
}
