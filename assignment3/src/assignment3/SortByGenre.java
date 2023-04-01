package assignment3;

import java.util.*;

public class SortByGenre implements Comparator<Movie> {
  public int compare(Movie m1, Movie m2) {
    return m1.genre.compareTo(m2.genre);
  }
}
