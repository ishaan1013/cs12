package isu;

import java.util.ArrayList;
import java.util.UUID;

public class BucM extends Tower {
  // dart monkey

  public BucM(int x, int y) {
    super(x, y, 80, 80, "buc", 100, 80, new ArrayList<Upgrade>(), false, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();
    String id = UUID.randomUUID().toString();

    upg.add(new Upgrade(id, 0, false, 200, "Sharp Darts", "Damage", 0, 1, 0, false, false));
    upg.add(new Upgrade(id, 1, true, 400, "Radar", "Range/Camo", 0, 0, 30, true, false));
    upg.add(new Upgrade(id, 2, true, 600, "Rapid Fire", "Fire Rate", 1, 0, 0, false, false));
    upg.add(new Upgrade(id, 3, true, 1500, "Battleship", "Max", 0, 6, 0, false, true));

    this.setUpgrades(upg);
    this.setID(id);

  }
}
