package assignment4;

import java.util.Comparator;

public class compareByHp implements Comparator<Card> {
  public int compare(Card a, Card b) {
    return a.getHp() - b.getHp();
  }
}
