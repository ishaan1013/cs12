package assignment3;

import java.util.Comparator;

public class SortByRating implements Comparator<Movie> {
  public int compare(Movie m1, Movie m2) {
    return m1.rating - m2.rating < 0 ? -1 : m1.rating - m2.rating > 0 ? 1 : 0;
  }
}
