package assignment4;


import java.util.*;

public class Album {
  private int num;
  private int capacity;
  private ArrayList<Card> cards;
  private int hp;
  private Date date;

  //getter methods
  public int getNum() {
    return num;
  }

  public int getCapacity() {
    return capacity;
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public int getHp() {
    return hp;
  }

  public Date getDate() {
    return date;
  }


  // constructor
  public Album(int num, Date date, int capacity, ArrayList<Card> cards) {
    this.num = num;
    this.date = date;
    this.capacity = capacity;
    this.cards = cards;
    this.hp = 0;

    // add up total hp
    if (cards != null) {
      for (Card card : cards) {
        this.hp += card.getHp();
      }
    }
  }

  // add a card to the album
  // takes in a pre-made card object
  // void
  public void addCard(Card c) {
    cards.add(c);
  }

  // remove a card from the album
  // takes in a pre-made card object
  // void
  public void removeCard(Card c) {
    cards.remove(c);
  }

  // equals (mostly used in indexOf in Driver)
  // parameter is an object to compare to
  // returns if equal or not
  public boolean equals(Object o) {
    Album other = (Album) o;
    int num = other.getNum();
    return this.num == num;
  }

  // no edit method here, will be in the attack class
  // no toString because im doing graphics


}
