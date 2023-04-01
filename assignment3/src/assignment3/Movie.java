package assignment3;

public class Movie implements Comparable<Movie> {

  public String title;
  public String genre;
  public double rating;

  public Movie(String title, String genre, double rating) {
    this.title = title;
    this.genre = genre;
    this.rating = rating;
  }

  public int compareTo(Movie m) {
    return this.title.compareTo(m.title);
  }


}
