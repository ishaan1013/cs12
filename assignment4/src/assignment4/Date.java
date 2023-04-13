package assignment4;

public class Date {

  int m;
  int d;
  int y;

  public Date(String dateString) {
    String[] date = dateString.split("/");

    //todo check for invalid dates
    m = Integer.parseInt(date[0]);
    d = Integer.parseInt(date[1]);
    y = Integer.parseInt(date[2]);
  }

}
