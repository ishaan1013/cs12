package assignment4;

import java.util.*;

public class Card implements Comparable<Card> {

  private int hp;
  private String name;
  private String type;
  private Date date;
  private ArrayList<Attack> attacks;

  // getter methods
  public int getHp() {
    return hp;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public Date getDate() {
    return date;
  }

  public ArrayList<Attack> getAttacks() {
    return attacks;
  }

  public Card(String name, int hp, String type, Date date, ArrayList<Attack> attacks) {
    this.name = name;
    this.hp = hp;
    this.type = type;
    this.date = date;
    this.attacks = attacks;
  }

  // equals (mostly used in indexOf in Driver)
  // parameter is an object to compare to
  // returns if equal or not
  public boolean equals(Object o) {
    Card other = (Card) o;
    boolean attacksEqual = this.attacks.size() == other.getAttacks().size();
    if (!attacksEqual) return false;
    for (int i = 0; i < this.attacks.size(); i++) {
      if (!this.attacks.get(i).equals(other.getAttacks().get(i))) {
        return false;
      }
    }
    return this.hp == other.getHp() &&
        this.name.equals(other.getName()) &&
        this.type.equals(other.getType()) &&
        this.date.equals(other.getDate());
  }

  // natural sorting order
  public int compareTo(Card other) {
    return this.name.compareTo(other.name);
  }
}
