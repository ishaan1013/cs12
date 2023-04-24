package assignment4;

public class Date {

  private int m;
  private int d;
  private int y;

  public int getMonth() {
    return m;
  }

  public int getDay() {
    return d;
  }

  public int getYear() {
    return y;
  }

  // equals (mostly used in indexOf in Driver)
  // parameter is an object to compare to
  // returns if equal or not
  public boolean equals(Object o) {
    Date other = (Date) o;
    return this.m == (other.getMonth()) &&
        this.d == other.getDay() &&
        this.y == other.getYear();
  }

  // checks if a date string is valid (used before creating a Date object)
  // parameter is a date string to verify
  // returns if valid or not
  public static boolean isValid(String dateString) {
    String[] date = dateString.split("/");

    try {
      if (date.length != 3) return false;

      int month = Integer.parseInt(date[0]);
      int day = Integer.parseInt(date[1]);
      int year = Integer.parseInt(date[2]);

      if (year < 0 || year > 2023) return false;
      if (month < 1 || month > 12) return false;
      if (day < 1 || day > 31) return false;
      if (month == 2) {
        if (day > 28) return false;
      }
      if (month == 4 || month == 6 || month == 9 || month == 11) {
        if (day > 30) return false;
      }
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // constructor
  // parameter is a valid datestring, splits with /
  public Date(String dateString) {
    String[] date = dateString.split("/");

    int month = Integer.parseInt(date[0]);
    int day = Integer.parseInt(date[1]);
    int year = Integer.parseInt(date[2]);
    m = month;
    d = day;
    y = year;


  }

  // todo pad zeros
  public String toString() {
    return String.format("%02d", m) + "/" + String.format("%02d", d) + "/" + String.format("%04d", y);
  }

}
