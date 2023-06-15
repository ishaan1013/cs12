package isu;

import java.util.ArrayList;

public class BucM extends Monkey {
  // dart monkey

  public BucM(int x, int y) {
    super(x, y, 80, 80, "buc", 100, 80, new ArrayList<Upgrade>(), false, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();

    upg.add(new Upgrade(200, "Sharp Darts", "Damage", 0, 1, 0, false, false));
    upg.add(new Upgrade(400, "Radar", "Range/Camo", 0, 0, 30, true, false));
    upg.add(new Upgrade(600, "Rapid Fire", "Fire Rate", 1, 0, 0, false, false));
    upg.add(new Upgrade(1500, "Battleship", "Max", 0, 6, 0, false, true));

    this.setUpgrades(upg);

  }
}
