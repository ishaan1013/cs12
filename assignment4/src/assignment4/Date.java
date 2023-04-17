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

  public Date(String dateString) {
    // check invalid dates
    // not used for this part of the assignment
    String[] date = dateString.split("/");

    try {
      int month = Integer.parseInt(date[0]);
      int day = Integer.parseInt(date[1]);
      int year = Integer.parseInt(date[2]);
      boolean error = false;

      if (year < 0 || year > 2023) error = true;
      if (month < 1 || month > 12) error = true;
      if (month == 2) {
        if (day > 28) error = true;
      }
      if (month == 4 || month == 6 || month == 9 || month == 11) {
        if (day > 30) error = true;
      }
      if (day < 1 || day > 31) error = true;

      if (!error) {
        m = month;
        d = day;
        y = year;
      }


    } catch (NumberFormatException e) {
      //something
    }

  }

  // todo pad zeros
  public String toString() {
    return m + "/" + d + "/" + y;
  }

}
