package isu;

import java.util.ArrayList;

public class DartM extends Monkey {
  // dart monkey

  public DartM(int x, int y) {
    super(x, y, 70, 70, "dart", 60, 50, new ArrayList<Upgrade>(), false, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();

    upg.add(new Upgrade(100, "Long Range Darts", 0, 0, 15, false, false));
    upg.add(new Upgrade(300, "Fast Arm", 1, 0, 0, false, false));
    upg.add(new Upgrade(500, "Night Vision Goggles", 0, 0, 0, true, false));
    upg.add(new Upgrade(800, "Spike-O-Pult", 0, 3, 0, false, true));

    this.setUpgrades(upg);

  }
}
