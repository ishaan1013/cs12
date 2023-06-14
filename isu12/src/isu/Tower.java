package isu;

import java.util.ArrayList;

public abstract class Tower {
  private int x;
  private int y;
  private int width;
  private int height;
  private String pathname;

  private int range;

  private int cost;

  private ArrayList<Upgrade> upgrades;

  public Tower(int x, int y, int width, int height, String pathname, int range, int cost, ArrayList<Upgrade> upgrades) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.pathname = "assets/monkeys/" + pathname + "/";
    this.range = range;
    this.cost = cost;
    this.upgrades = upgrades;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getPathname() {
    return pathname;
  }

  public int getRange() {
    return range;
  }

  public int getCost() {
    return cost;
  }

  public ArrayList<Upgrade> getUpgrades() {
    return upgrades;
  }


  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public void setUpgrades(ArrayList<Upgrade> upgrades) {
    this.upgrades = upgrades;
  }

}
