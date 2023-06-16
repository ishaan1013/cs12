package isu;

import java.util.ArrayList;
import java.util.UUID;

public class DartM extends Tower {
  // dart monkey

  public DartM(int x, int y) {
    super(x, y, 70, 70, "dart", 80, 50, new ArrayList<Upgrade>(), false, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();
    String id = UUID.randomUUID().toString();

    upg.add(new Upgrade(id, 0, false, 100, "Long Range Darts", "Range", 0, 0, 30, false, false));
    upg.add(new Upgrade(id, 1, true, 300, "Fast Arm", "Fire Rate", 1, 0, 0, false, false));
    upg.add(new Upgrade(id, 2, true, 500, "Night Vision Goggles", "Camo", 0, 0, 0, true, false));
    upg.add(new Upgrade(id, 3, true, 800, "Spike-O-Pult", "Max", 0, 4, 0, false, true));

    this.setUpgrades(upg);
    this.setID(id);

  }
}
