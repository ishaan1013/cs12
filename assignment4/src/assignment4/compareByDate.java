package assignment4;

import java.util.Comparator;

public class compareByDate implements Comparator<Card> {
  public int compare(Card a, Card b) {
    Date dateA = a.getDate();
    Date dateB = b.getDate();
    if (dateA.getYear() == dateB.getYear()) {
      if (dateA.getMonth() == dateB.getMonth()) {
        return dateA.getDay() - dateB.getDay();
      } else {
        return dateA.getMonth() - dateB.getMonth();
      }
    } else {
      return dateA.getYear() - dateB.getYear();
    }
  }
}
