package isu;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tower {
  private int x;
  private int y;
  private int width;
  private int height;
  private String pathname;

  private int range;
  private int cost;

  private boolean selected;

  private ArrayList<Upgrade> upgrades;

  public Tower(int x, int y, int width, int height, String pathname, int range, int cost, ArrayList<Upgrade> upgrades) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.pathname = pathname;
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
    return "assets/monkeys/" + pathname + "/";
  }

  public String getName() {
    if (pathname.equals("buc")) return "Buccaneer";
    return pathname.substring(0, 1).toUpperCase() + pathname.substring(1) + " Monkey";
  }

  public int getRange() {
    return range;
  }

  public int getCost() {
    return cost;
  }

  public boolean isSelected() {
    return selected;
  }

  public ArrayList<Upgrade> getUpgrades() {
    return upgrades;
  }

  public Rectangle getHitbox() {
    // return a rectangle based on hitbox
    // if width is 70, hitbox is 40x40
    // if width is 85, hitbox is 48x48
    // hitbox should be centered in the tower

    int offset;
    if (this.width == 70) {
      offset = 20;
    } else {
      offset = 24;
    }

    return new Rectangle(this.x - offset, this.y - offset, offset * 2, offset * 2);

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

  public void setSelected(boolean selected) {
    this.selected = selected;

    System.out.println("selected: " + this.selected + " for " + this.pathname);
  }

  public void setUpgrades(ArrayList<Upgrade> upgrades) {
    this.upgrades = upgrades;
  }

}
