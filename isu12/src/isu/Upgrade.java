package isu;

public class Upgrade {

  private int cost;
  private String name;

  private int fireRateChange;
  private int damageChange;
  private int rangeChange;
  private boolean camoChange;
  private boolean leadChange;

  public Upgrade(int cost, String name, int fireRateChange, int damageChange, int rangeChange, boolean camoChange, boolean leadChange) {
    this.cost = cost;
    this.name = name;
    this.fireRateChange = fireRateChange;
    this.damageChange = damageChange;
    this.rangeChange = rangeChange;
    this.camoChange = camoChange;
    this.leadChange = leadChange;
  }

}
