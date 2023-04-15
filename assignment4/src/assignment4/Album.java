package assignment4;


import java.util.*;

public class Album {
  private int num;
  private int capacity;
  private ArrayList<Card> cards;
  private int hp;
  private Date date;

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

  public Album(int num, Date date, int capacity, ArrayList<Card> cards) {
    this.num = num;
    this.date = date;
    this.capacity = capacity;
    this.cards = cards;
    this.hp = 0;

    for (Card card : cards) {
      this.hp += card.getHp();
    }
  }

  void displayInfo(int num, Date date, int capacity, int cards, int hp) {
  }

  void displayCards(ArrayList<Card> cards) {
  }

  void addCard(int hp, String name, String type, Date date, ArrayList<Attack> attacks) {
  }

  void remove(String type, String item) {
  }

  void edit(String name, String attack, String field, String value) {
  }

  void sort(String type) {
  }


}
