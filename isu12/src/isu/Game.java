package isu;

import java.util.*;
import java.util.stream.*;

public class Game {

  private int map;
  private int[] startPos;
  private char[] dir;
  private int[][] path;
  private int[][] land;
  private int[][] water;

  private int hp;
  private int money;
  private int round;

  private boolean pause;
  private boolean spedUp;

  private ArrayList<Tower> towers;
  private ArrayList<Bloon> bloons;
  private String placing;

  public Game(int map) {
    this.map = map;

    if (map == 0) {
      this.startPos = new int[]{-1, 2};
      this.dir = new char[]{'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'D', 'D', 'L', 'L', 'L', 'D', 'D', 'D', 'D', 'L', 'L', 'L', 'U', 'U', 'U', 'L', 'L', 'D', 'D', 'D', 'L', 'L', 'U', 'U', 'L', 'L', 'L', 'L'};
      this.path = new int[][]{
          {},
          {},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
          {13},
          {10, 11, 12, 13},
          {6, 7, 8, 11},
          {0, 1, 2, 3, 5, 7, 10},
          {3, 5, 7, 10},
          {3, 4, 5, 7, 8, 9, 10},
          {}
      };
      this.land = new int[][]{
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
          {14},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 14},
          {0, 1, 2, 3, 4, 8, 9, 14},
          {4, 6, 8, 9, 14},
          {0, 1, 2, 4, 6, 8, 9, 14},
          {0, 1, 2, 6, 14},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
      };
      this.water = new int[][]{
          {},
          {},
          {},
          {},
          {},
          {11, 12, 13},
          {11, 12, 13},
          {11, 12, 13},
          {11, 12, 13},
          {}
      };
    } else {
      this.startPos = new int[]{15, 1};

      this.dir = new char[]{'L', 'L', 'L', 'L', 'D', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'D', 'D', 'D', 'D', 'D', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'U', 'U', 'U', 'R', 'R', 'D', 'D', 'D', 'R', 'R', 'U', 'U', 'U', 'R', 'R'};
      this.path = new int[][]{
          {},
          {11, 12, 13, 14},
          {2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
          {2},
          {2, 9, 10, 11, 13, 14},
          {2, 9, 11, 13},
          {2, 9, 11, 13},
          {2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13},
          {},
          {}
      };
      this.land = new int[][]{
          {10, 11, 12, 13, 14},
          {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
          {1, 12, 13, 14},
          {1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
          {1, 3, 4, 5, 6, 7, 8, 12},
          {5, 6, 7, 8, 10, 12, 14},
          {5, 6, 7, 8, 10, 12, 14},
          {10, 14},
          {5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
          {}
      };

      this.water = new int[][]{
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
          {0},
          {0},
          {0},
          {0},
          {0, 1, 3, 4},
          {0, 1, 3, 4},
          {0, 1},
          {0, 1, 2, 3, 4},
          {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
      };
    }

    this.hp = 200;
    this.money = 800;
    this.round = 1;
    this.pause = false;
    this.spedUp = false;
    this.towers = new ArrayList<Tower>();
    this.bloons = new ArrayList<Bloon>();
    this.placing = null;
  }

  public int getHp() {
    return hp;
  }

  public int getMoney() {
    return money;
  }

  public int getRound() {
    return round;
  }

  public boolean getPause() {
    return pause;
  }

  public boolean getSpedUp() {
    return spedUp;
  }

  public ArrayList<Tower> getTowers() {
    return towers;
  }

  public ArrayList<Bloon> getBloons() {
    return bloons;
  }

  public String getPlacing() {
    return placing;
  }

  public void lose(int hp) {
    this.hp -= hp;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public boolean pauseResume() {
    this.pause = !this.pause;
    return this.pause;
    // return the new state
  }

  public boolean speedChange() {
    this.spedUp = !this.spedUp;
    return this.spedUp;
  }

  public void addTower(Tower t) {
    this.towers.add(t);
  }

  public void removeTower(Tower t) {
    this.towers.remove(t);
  }

  public void setBloons(ArrayList<Bloon> bloons) {
    this.bloons = bloons;
  }

  public void setPlacing(String placing) {
    this.placing = placing;
  }

  public boolean isCoordValid(int x, int y, int width, boolean water) {
    // 40x40 hitbox for 70 (monkeys)
    // 48x48  hitbox for 80/85 (buc & others)
    int offset;

    if (width == 70) {
      offset = 20;
    } else {
      offset = 24;
    }

    // check if on board (border offset)
    if (x > (900 - offset)) return false;
    if (x < offset) return false;
    if (y > (600 - offset)) return false;
    if (y < offset) return false;


    // check tower collisions
    for (Tower t : this.towers) {
      boolean collisionX = (x < t.getX() + (offset * 2) && x >= t.getX()) || (x > t.getX() - (offset * 2) && x <= t.getX());
      boolean collisionY = (y < t.getY() + (offset * 2) && y >= t.getY()) || (y > t.getY() - (offset * 2) && y <= t.getY());

      System.out.println("collisionX: " + collisionX + " collisionY: " + collisionY);
      if (collisionX && collisionY) return false;
    }

    // check overextending onto an invalid square
    boolean top = false, bottom = false, left = false, right = false;

    int row = y / 60;
    int rowY = y % 60;
    // check if it's on an edge based on offset
    if (rowY < offset) {
      top = row > 0;
    } else if (rowY > (60 - offset)) {
      bottom = row < 9;
    }

    int col = x / 60;
    int colX = x % 60;
    if (colX < offset) {
      left = col > 0;
    } else if (colX > (60 - offset)) {
      right = col < 9;
    }

    // check if it's on a path
    if (IntStream.of(this.path[row]).anyMatch(c -> c == col)) return false;

    // check if it's on the correct terrain
    if (water) {
      if (IntStream.of(this.land[row]).anyMatch(c -> c == col)) return false;
    } else {
      if (IntStream.of(this.water[row]).anyMatch(c -> c == col)) return false;
    }

    // if monkey is on an edge
    if (water) {
      if (top) {
        if (IntStream.of(this.path[row - 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(this.land[row - 1]).anyMatch(c -> c == col)) return false;
      }
      if (bottom) {
        if (IntStream.of(this.path[row + 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(this.land[row + 1]).anyMatch(c -> c == col)) return false;
      }
      if (left) {
        if (IntStream.of(this.path[row]).anyMatch(c -> c == col - 1)) return false;
        if (IntStream.of(this.land[row]).anyMatch(c -> c == col - 1)) return false;
      }
      if (right) {
        if (IntStream.of(this.path[row]).anyMatch(c -> c == col + 1)) return false;
        if (IntStream.of(this.land[row]).anyMatch(c -> c == col + 1)) return false;
      }
    } else {
      if (top) {
        if (IntStream.of(this.path[row - 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(this.water[row - 1]).anyMatch(c -> c == col)) return false;
      }
      if (bottom) {
        if (IntStream.of(this.path[row + 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(this.water[row + 1]).anyMatch(c -> c == col)) return false;
      }
      if (left) {
        if (IntStream.of(this.path[row]).anyMatch(c -> c == col - 1)) return false;
        if (IntStream.of(this.water[row]).anyMatch(c -> c == col - 1)) return false;
      }
      if (right) {
        if (IntStream.of(this.path[row]).anyMatch(c -> c == col + 1)) return false;
        if (IntStream.of(this.water[row]).anyMatch(c -> c == col + 1)) return false;
      }
    }

    return true;

  }
}
