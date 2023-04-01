package assignment3;

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {

    ArrayList<Movie> movies = new ArrayList<Movie>();

    String line;
    try {
      BufferedReader file = new BufferedReader(new FileReader("input.txt"));
      while ((line = file.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);

        int count = st.countTokens();
        if (count >= 3) {
          try {
            String ratingInput = st.nextToken();
            int percentIndex = ratingInput.indexOf("%");
            if (percentIndex == -1 || percentIndex != ratingInput.length() - 1 || ratingInput.length() < 2) {
              System.out.println("0 Skipping line (invalid): " + line);
            } else {
              double rating = Double.parseDouble(ratingInput.substring(0, percentIndex));
              StringBuilder title = new StringBuilder();
              for (int i = 1; i < count - 1; i++) {
                title.append(st.nextToken());
                title.append(" ");
              }
              title.setLength(title.length() - 1);
              String genre = st.nextToken();
              movies.add(new Movie(title.toString(), genre, rating));
            }
          } catch (NumberFormatException e) {
            System.out.println("1 Skipping line (invalid): " + line);
          }
        } else {
          System.out.println("2 Skipping line (invalid): " + line);
        }

      }
      file.close();

      System.out.println("");

      Collections.sort(movies);
      for (Movie m : movies) {
        System.out.println(m.title + " " + m.genre + " " + m.rating);
      }

      System.out.println("");

      ArrayList<Movie> moviesByGenre = new ArrayList<Movie>(movies);
      Collections.sort(moviesByGenre, new SortByGenre());
      for (Movie m : moviesByGenre) {
        System.out.println(m.title + " " + m.genre + " " + m.rating);
      }

      System.out.println("");

      ArrayList<Movie> moviesByRating = new ArrayList<Movie>(movies);
      Collections.sort(moviesByRating, new SortByRating());
      for (Movie m : moviesByRating) {
        System.out.println(m.title + " " + m.genre + " " + m.rating);
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String findType = "";
      while (!findType.equals("exit")) {
        System.out.print("Search for title or genre? (or exit): ");
        line = br.readLine();
        if (line.equalsIgnoreCase("title")) {
          findType = "title";

          do {
            System.out.print("Enter title to search for: ");
            line = br.readLine();
            int pos = Collections.binarySearch(movies, new Movie(line, "", 0));

          } while (!line.equals("exit"));

        } else if (line.equalsIgnoreCase("genre")) {
          findType = "genre";

        } else if (line.equalsIgnoreCase("exit")) {
          findType = "exit";
        } else {
          System.out.println("Invalid input");
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }


  }

}