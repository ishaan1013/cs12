package isu;

import java.util.ArrayList;
import java.util.UUID;

public class NinjaM extends Tower {
  // dart monkey

  public NinjaM(int x, int y) {
    super(x, y, 70, 70, "ninja", 90, 80, new ArrayList<Upgrade>(), true, false, new Projectile(), 2, 1);

    setupUpgrades();
  }

  private void setupUpgrades() {
    ArrayList<Upgrade> upg = this.getUpgrades();
    String id = UUID.randomUUID().toString();

    upg.add(new Upgrade(id, 0, false, 300, "Ninja Speed", "Fire Rate", 1, 0, 0, false, false));
    upg.add(new Upgrade(id, 1, true, 600, "Enhanced Vision", "Range", 0, 0, 45, false, false));
    upg.add(new Upgrade(id, 2, true, 900, "Hot Shurikens", "Damage/Lead", 0, 1, 0, false, true));
    upg.add(new Upgrade(id, 3, true, 2000, "Bloonjitsu", "Max", 1, 8, 0, false, false));

    this.setUpgrades(upg);
    this.setID(id);

  }
}
