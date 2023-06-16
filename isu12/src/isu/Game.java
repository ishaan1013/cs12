package isu;

import javax.swing.*;
import java.util.*;
import java.util.stream.*;

public class Game {

  private static int map;
  private static int[] startPos;
  private static char[] dir;
  private static int[][] path;
  private static int[][] land;
  private static int[][] water;

  private static int hp = 200;
  private static int money = 800;
  private static int round = 1;

  private static boolean pause = true;
  private static boolean spedUp = false;

  private static ArrayList<Tower> towers = new ArrayList<>();
  private static ArrayList<Bloon> bloons = new ArrayList<>();
  private static String placing = null;
  private static int selected;

  // public here to revalidate in other classes & methods
  public static JPanel upgrades;

  public static void initGame(int map) {
    Game.map = map;

    if (map == 0) {
      Game.startPos = new int[]{-1, 2};
      Game.dir = new char[]{'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'D', 'D', 'L', 'L', 'L', 'D', 'D', 'D', 'D', 'L', 'L', 'L', 'U', 'U', 'U', 'L', 'L', 'D', 'D', 'D', 'L', 'L', 'U', 'U', 'L', 'L', 'L', 'L'};
      Game.path = new int[][]{
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
      Game.land = new int[][]{
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
      Game.water = new int[][]{
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
      Game.startPos = new int[]{15, 1};

      Game.dir = new char[]{'L', 'L', 'L', 'L', 'D', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'D', 'D', 'D', 'D', 'D', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'U', 'U', 'U', 'R', 'R', 'D', 'D', 'D', 'R', 'R', 'U', 'U', 'U', 'R', 'R'};
      Game.path = new int[][]{
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
      Game.land = new int[][]{
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

      Game.water = new int[][]{
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

  }

  public static int getHp() {
    return hp;
  }

  public static int getMoney() {
    return money;
  }

  public static int getRound() {
    return round;
  }

  public static boolean getPause() {
    return pause;
  }

  public static boolean getSpedUp() {
    return spedUp;
  }

  public static ArrayList<Tower> getTowers() {
    return towers;
  }

  public static ArrayList<Bloon> getBloons() {
    return bloons;
  }

  public static String getPlacing() {
    return placing;
  }

  public static void lose(int hp) {
    Game.hp -= hp;
  }

  public static void setMoney(int money) {
    Game.money = money;
  }

  public static void setRound(int round) {
    Game.round = round;
  }

  public static boolean pauseResume() {
    Game.pause = !Game.pause;
    return Game.pause;
    // return the new state
  }

  public static boolean speedChange() {
    Game.spedUp = !Game.spedUp;
    return Game.spedUp;
  }

  public static void addTower(Tower t) {
    Game.towers.add(t);
  }

  public static void removeTower(Tower t) {
    Game.towers.remove(t);
  }

  public static void setBloons(ArrayList<Bloon> bloons) {
    Game.bloons = bloons;
  }

  public static void setPlacing(String placing) {
    Game.placing = placing;
  }

  public static boolean isCoordValid(int x, int y, int width, boolean water) {
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
    for (Tower t : Game.towers) {
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
    if (IntStream.of(Game.path[row]).anyMatch(c -> c == col)) return false;

    // check if it's on the correct terrain
    if (water) {
      if (IntStream.of(Game.land[row]).anyMatch(c -> c == col)) return false;
    } else {
      if (IntStream.of(Game.water[row]).anyMatch(c -> c == col)) return false;
    }

    // if monkey is on an edge
    if (water) {
      if (top) {
        if (IntStream.of(Game.path[row - 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(Game.land[row - 1]).anyMatch(c -> c == col)) return false;
      }
      if (bottom) {
        if (IntStream.of(Game.path[row + 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(Game.land[row + 1]).anyMatch(c -> c == col)) return false;
      }
      if (left) {
        if (IntStream.of(Game.path[row]).anyMatch(c -> c == col - 1)) return false;
        if (IntStream.of(Game.land[row]).anyMatch(c -> c == col - 1)) return false;
      }
      if (right) {
        if (IntStream.of(Game.path[row]).anyMatch(c -> c == col + 1)) return false;
        if (IntStream.of(Game.land[row]).anyMatch(c -> c == col + 1)) return false;
      }
    } else {
      if (top) {
        if (IntStream.of(Game.path[row - 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(Game.water[row - 1]).anyMatch(c -> c == col)) return false;
      }
      if (bottom) {
        if (IntStream.of(Game.path[row + 1]).anyMatch(c -> c == col)) return false;
        if (IntStream.of(Game.water[row + 1]).anyMatch(c -> c == col)) return false;
      }
      if (left) {
        if (IntStream.of(Game.path[row]).anyMatch(c -> c == col - 1)) return false;
        if (IntStream.of(Game.water[row]).anyMatch(c -> c == col - 1)) return false;
      }
      if (right) {
        if (IntStream.of(Game.path[row]).anyMatch(c -> c == col + 1)) return false;
        if (IntStream.of(Game.water[row]).anyMatch(c -> c == col + 1)) return false;
      }
    }

    return true;
  }

  public static void setHp(int hp) {
    Game.hp = hp;
  }

}
