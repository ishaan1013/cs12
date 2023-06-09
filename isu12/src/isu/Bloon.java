package isu;

public abstract class Bloon {
  private int x;
  private int y;
  private int width;
  private int height;

  // track progress in the path array
  private int pathIndex;

  private int speed;
  private int hp;
  private boolean camo;

  private Bloon[] children;
}
