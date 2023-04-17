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

  // natural sorting order
  public int compareTo(Card other) {
    return this.name.compareTo(other.name);
  }
}
