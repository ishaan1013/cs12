package isu;

import java.util.ArrayList;

public abstract class Monkey extends Tower {
  private int pops;
  private boolean camo;
  private boolean lead;

  private Projectile projectile;
  private int fireRate;
  private int damage;

  public Monkey(int x, int y, int width, int height, String pathname, int range, int cost, ArrayList<Upgrade> upgrades, boolean camo, boolean lead, Projectile projectile, int fireRate, int damage) {
    super(x, y, width, height, pathname, range, cost, upgrades);

    this.pops = 0;
    this.camo = camo;
    this.lead = lead;
    this.projectile = projectile;
    this.fireRate = fireRate;
    this.damage = damage;
  }

  public int getPops() {
    return pops;
  }

  public boolean getCamo() {
    return camo;
  }

  public boolean getLead() {
    return lead;
  }

  public Projectile getProjectile() {
    return projectile;
  }

  public int getFireRate() {
    return fireRate;
  }

  public int getDamage() {
    return damage;
  }

  public void setPops(int pops) {
    this.pops = pops;
  }

  public void setCamo(boolean camo) {
    this.camo = camo;
  }

  public void setLead(boolean lead) {
    this.lead = lead;
  }

  public void setProjectile(Projectile projectile) {
    this.projectile = projectile;
  }

  public void setFireRate(int fireRate) {
    this.fireRate = fireRate;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}
