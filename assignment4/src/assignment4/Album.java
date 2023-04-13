package assignment4;


import java.util.*;

public class Album {
  int num;
  int capacity;
  ArrayList<Card> cards;
  int hp;
  Date date;

  public Album(int num, Date date, int capacity, ArrayList<Card> cards) {
    this.num = num;
    this.date = date;
    this.capacity = capacity;
    this.cards = cards;
    this.hp = 0;
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
