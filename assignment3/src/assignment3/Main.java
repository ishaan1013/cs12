// Ishaan Dey
// April 1 2023
// ASSIGNMENT 3 WITH BONUS
// Search by title or genre from a list of movies, and sorting through duplicates

package assignment3;

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {

    ArrayList<Movie> movies = new ArrayList<>();

    String line;
    try {
      BufferedReader file = new BufferedReader(new FileReader("input.txt"));

      while ((line = file.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);

        int count = st.countTokens();
        if (count >= 3) {
          String ratingInput = st.nextToken();
          int percentIndex = ratingInput.indexOf("%");
          if (percentIndex == -1 || percentIndex != ratingInput.length() - 1 || ratingInput.length() < 2) {
            // check if rating format is valid
            // no %, % not at the end, or less than 2 characters
            System.out.println("Skipping line (invalid rating format): " + line);
          } else {
            try {
              double rating = Double.parseDouble(ratingInput.substring(0, percentIndex));
              if (rating < 0 || rating > 100) {
                // check if rating is within range
                System.out.println("Skipping line (rating must be 0 to 100%): " + line);
              } else {
                StringBuilder titleSb = new StringBuilder();
                for (int i = 1; i < count - 1; i++) {
                  titleSb.append(st.nextToken());
                  titleSb.append(" ");
                }
                String title = titleSb.toString().trim();
                if (title.equalsIgnoreCase("exit"))
                  System.out.println("Skipping line (title cannot be 'exit'): " + line);
                else {
                  String genre = st.nextToken().toLowerCase();
                  movies.add(new Movie(title, genre, rating));
                }
              }
            } catch (NumberFormatException e) {
              System.out.println("Skipping line (invalid rating number): " + line);
            }
          }
        } else {
          System.out.println("Skipping line (input format is 'rating title genre'): " + line);
        }

      }
      file.close();

      if (movies.size() == 0) {
        System.out.println("No movies added. Exiting");
      } else {
        Collections.sort(movies);
        userActions(movies);
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }


  }

  // handles all searches etc after reading in the file
  // takes in list of movies
  // no return
  public static void userActions(ArrayList<Movie> movies) throws IOException {
    String line;

    ArrayList<Movie> moviesByGenre = new ArrayList<>(movies);
    Collections.sort(moviesByGenre, new SortByGenre());

    ArrayList<Movie> moviesByRating = new ArrayList<>(movies);
    Collections.sort(moviesByRating, new SortByRating());

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String findType = "";
    while (!findType.equals("exit")) {
      System.out.print("\nSearch for title or genre? (or exit): ");
      line = br.readLine().trim();
      if (line.equalsIgnoreCase("title")) {
        findType = "title";

        while (!line.equals("exit")) {
          System.out.print("\nEnter title to search for (or exit): ");
          line = br.readLine().trim();
          if (line.equalsIgnoreCase("exit")) {
            break;
          }
          int pos = Collections.binarySearch(movies, new Movie(line, "", 0));
          // check: movie doesn't exist or no input is given
          if (pos < 0 || line.isEmpty()) {
            System.out.println("Movie not found");
          } else {

            // check on either side for duplicate titles
            ArrayList<Movie> duplicates = new ArrayList<>();
            duplicates.add(movies.get(pos));
            for (int i = pos - 1; i >= 0; i--) {
              if (movies.get(i).title.equalsIgnoreCase(line))
                duplicates.add(movies.get(i));
              else
                break;
            }
            for (int i = pos + 1; i < movies.size(); i++) {
              if (movies.get(i).title.equalsIgnoreCase(line))
                duplicates.add(movies.get(i));
              else
                break;
            }
            if (duplicates.size() > 1) {

              String prevGenre = duplicates.get(0).genre;
              boolean sameGenre = true;
              double prevRating = duplicates.get(0).rating;
              boolean sameRating = true;
              for (Movie m : duplicates) {
                if (!m.title.equals(prevGenre)) {
                  sameGenre = false;
                }
                if (m.rating != prevRating) {
                  sameRating = false;
                }
              }

              //no need to check if both are true because then it doesn't matter which one you sort by
              if (sameRating) {
                line = "genre";
                System.out.println("Multiple movies found. Automatically sorting by genre.");
              } else if (sameGenre) {
                line = "rating";
                System.out.println("Multiple movies found. Automatically sorting by rating.");
              } else {
                System.out.print("Multiple movies found. Sort by genre or rating? ");
                line = br.readLine().trim();
                while (!line.equalsIgnoreCase("genre") && !line.equalsIgnoreCase("rating")) {
                  System.out.print("Invalid input. Sort by genre or rating? ");
                  line = br.readLine().trim();
                }
              }


              if (line.equalsIgnoreCase("genre")) {
                Collections.sort(duplicates, new SortByGenre());
                for (Movie m : duplicates) {
                  printMovie(movies, moviesByRating, m);
                }
              } else {
                Collections.sort(duplicates, new SortByRating());
                Collections.reverse(duplicates);
                for (Movie m : duplicates) {
                  printMovie(movies, moviesByRating, m);
                }
              }

            } else {
              printMovie(movies, moviesByRating, movies.get(pos));
            }

          }

        }

      } else if (line.equalsIgnoreCase("genre")) {
        findType = "genre";

        while (!line.equals("exit")) {
          System.out.print("\nEnter genre to search for (or exit): ");
          line = br.readLine().trim().toLowerCase();
          if (line.isEmpty())
            System.out.println("Enter a genre.");
          else if (!line.equals("exit")) {
            int pos = Collections.binarySearch(moviesByGenre, new Movie("", line, 0), new SortByGenre());
            // check: movie doesn't exist or no input is given
            if (pos < 0) {
              System.out.println("Movies with genre " + line + " not found");
            } else {
              ArrayList<Movie> moviesWithGenre = new ArrayList<>();
              moviesWithGenre.add(moviesByGenre.get(pos));

              for (int i = pos - 1; i >= 0; i--) {
                if (moviesByGenre.get(i).genre.equals(line)) {
                  moviesWithGenre.add(0, moviesByGenre.get(i));
                } else {
                  break;
                }
              }
              for (int i = pos + 1; i < moviesByGenre.size(); i++) {
                if (moviesByGenre.get(i).genre.equals(line)) {
                  moviesWithGenre.add(moviesByGenre.get(i));
                } else {
                  break;
                }
              }

              if (moviesWithGenre.size() > 1) {

                String prevTitle = moviesWithGenre.get(0).title;
                boolean sameTitle = true;
                double prevRating = moviesWithGenre.get(0).rating;
                boolean sameRating = true;
                for (Movie m : moviesWithGenre) {
                  if (!m.title.equals(prevTitle)) {
                    sameTitle = false;
                  }
                  if (m.rating != prevRating) {
                    sameRating = false;
                  }
                }

                //no need to check if both are true because then it doesn't matter which one you sort by
                if (sameRating) {
                  line = "title";
                  System.out.println("Multiple movies found. Automatically sorting by title.");
                } else if (sameTitle) {
                  line = "rating";
                  System.out.println("Multiple movies found. Automatically sorting by rating.");
                } else {
                  System.out.print("Multiple movies found. Sort by title or rating? ");
                  line = br.readLine().trim();
                  while (!line.equalsIgnoreCase("title") && !line.equalsIgnoreCase("rating")) {
                    System.out.print("Invalid input. Sort by title or rating? ");
                    line = br.readLine().trim();
                  }
                }

                if (line.equalsIgnoreCase("title")) {
                  Collections.sort(moviesWithGenre);
                  for (Movie m : moviesWithGenre) {
                    printMovie(movies, moviesByRating, m);
                  }
                } else {
                  Collections.sort(moviesWithGenre, new SortByRating());
                  Collections.reverse(moviesWithGenre);
                  for (Movie m : moviesWithGenre) {
                    printMovie(movies, moviesByRating, m);
                  }
                }

              } else {
                printMovie(movies, moviesByRating, moviesWithGenre.get(0));
              }
            }

          }

        }

      } else if (line.equalsIgnoreCase("exit")) {
        findType = "exit";
      } else {
        System.out.println("Invalid input");
      }
    }

  }

  // print movie info and ranking in the movie list
  // takes in the movie list, the movie list sorted by rating, and the movie object to print
  // no return, just prints things
  public static void printMovie(ArrayList<Movie> movies, ArrayList<Movie> moviesByRating, Movie m) {
    int rank = Collections.binarySearch(moviesByRating, new Movie("", "", m.rating), new SortByRating());

    // check upwards for duplicates, and add to rank if found
    for (int i = rank + 1; i < moviesByRating.size(); i++) {
      if (moviesByRating.get(i).rating == m.rating)
        rank++;
      else
        break;
    }

    System.out.println("Movie title: " + m.title);
    System.out.println("Genre: " + m.genre);
    System.out.printf("Rating: %.1f%%%n", m.rating);
    System.out.println("Ranking: " + (movies.size() - rank) + " out of " + movies.size());
  }
}