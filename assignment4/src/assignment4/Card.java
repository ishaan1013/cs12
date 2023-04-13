package assignment4;

import java.util.*;

public class Card {

  int hp;
  String name;
  String type;
  Date date;
  ArrayList<Attack> attacks;

  public Card(String name, int hp, String type, Date date, ArrayList<Attack> attacks) {
    this.name = name;
    this.hp = hp;
    this.type = type;
    this.date = date;
    this.attacks = attacks;
  }
}
