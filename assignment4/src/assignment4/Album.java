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
  // takes in all the necessary info inputted by user
  // returns the card to be added
  public Card addCard(int hp, String name, String type, Date date, ArrayList<Attack> attacks) {
    return null;
  }

  // choose how to sort, then the method sorts the cards with that info (either by the natural order or with a comparator)
  // takes in the sorting type
  // void (updates the instance variable)
  public void sort(String sortBy) {
  }

  public boolean equals(Object o) {
    Album other = (Album) o;
    int num = other.getNum();
    return this.num == num;
  }

  // no edit method here, will be in the attack class
  // no toString because im doing graphics


}
