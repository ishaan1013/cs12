// Ishaan Dey
// March 21 2023
// Save the cat from become morbidly obese in the sink by navigating Ms. Wong's maze (house)

package assignment2;

import java.io.*;

public class Main {

  // global variable for the number of rows and columns in the grid
  static int rows, cols;

  public static void main(String[] args) {
    try {
      BufferedReader in = new BufferedReader(new
          FileReader("input.txt"));

      int houses = Integer.parseInt(in.readLine());

      for (int house = 0; house < houses; house++) {
        rows = Integer.parseInt(in.readLine());
        cols = Integer.parseInt(in.readLine());
        char[][] grid = new char[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        int[] w = new int[2];

        for (int i = 0; i < rows; i++) {
          String line = in.readLine();
          for (int j = 0; j < cols; j++) {
            grid[i][j] = line.charAt(j);
            // save start position
            if (line.charAt(j) == 'W') {
              w[0] = i;
              w[1] = j;
            }
          }
        }

        if (rows <= 1 && cols <= 1)
          System.out.println("The house does not exist.");
        else {
          String p = saveTheCat(grid, visited, w[0], w[1], "", "");
          // path will be an empty string if the cat is never reached
          if (p.equals(""))
            System.out.println("Suki is eternally trapped in the sink. 😐");
          else {
            System.out.println("Fastest # of steps: " + p.length());
            System.out.print("Direction:");
            for (char c : p.toCharArray()) {
              System.out.print(" " + c);
            }
            System.out.println("\n");
          }
        }
      }
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist");
    } catch (IOException e) {
      System.out.println("Reading error");
    }
  }

  // recursive method to save the cat
  // takes in the grid, visited array, current row, current column, current path, and fastest path so far
  // returns the fastest path, or an empty string if no path exists
  static String saveTheCat(char[][] grid, boolean[][] visited, int r, int c, String path, String fastest) {
    if (r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c] || grid[r][c] == 'X') {
      return fastest;
    }

    if (grid[r][c] == 'S') {
      // if there's no fastest path yet, or the current path is shorter than the fastest path, set the fastest path to the current path
      if (fastest.equals("") || path.length() < fastest.length()) {
        fastest = path;
      }
      return fastest;
    }

    visited[r][c] = true;
    // try all four directions
    // fastest will only be changed if the current path is shorter than the fastest path, or if there is no fastest path yet (due to the previous conditional stuff)
    fastest = saveTheCat(grid, visited, r + 1, c, path + "S", fastest);
    fastest = saveTheCat(grid, visited, r - 1, c, path + "N", fastest);
    fastest = saveTheCat(grid, visited, r, c + 1, path + "E", fastest);
    fastest = saveTheCat(grid, visited, r, c - 1, path + "W", fastest);
    visited[r][c] = false;

    return fastest;
  }
}