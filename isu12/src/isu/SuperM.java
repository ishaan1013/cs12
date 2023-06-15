package isu;

import java.util.ArrayList;

public class SuperM extends Monkey {
  // dart monkey

  public SuperM(int x, int y) {
    super(x, y, 70, 70, "super", 70, 500, new ArrayList<Upgrade>(), false, false, new Projectile(), 2, 4);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();

    upg.add(new Upgrade(600, "Super Vision", "Range/Camo", 0, 0, 30, true, false));
    upg.add(new Upgrade(1200, "Super Sharp Darts", "Damage/Lead", 0, 4, 0, false, true));
    upg.add(new Upgrade(2400, "Extreme Speed", "Fire Rate", 2, 0, 0, false, false));
    upg.add(new Upgrade(8000, "Tech Terror", "Max", 0, 16, 0, false, false));

    this.setUpgrades(upg);

  }
}
