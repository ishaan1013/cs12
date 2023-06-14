package isu;

import java.util.ArrayList;

public class NinjaM extends Monkey {
  // dart monkey

  public NinjaM(int x, int y) {
    super(x, y, 70, 70, "ninja", 60, 80, new ArrayList<Upgrade>(), true, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();

    upg.add(new Upgrade(300, "Ninja Speed", 1, 0, 0, false, false));
    upg.add(new Upgrade(600, "Enhanced Vision", 0, 0, 15, false, false));
    upg.add(new Upgrade(900, "Hot Shurikens", 0, 1, 0, false, true));
    upg.add(new Upgrade(2000, "Bloonjitsu", 1, 8, 0, false, false));

    this.setUpgrades(upg);

  }
}
