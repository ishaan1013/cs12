package assignment4;

import java.util.StringTokenizer;

public class Attack {
  private String name;
  private String desc;
  private String damage;

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public String getDamage() {
    return damage;
  }

  public Attack(String attackInfo, String attackDamage) {
    if (attackInfo.contains("-")) {
      StringTokenizer st = new StringTokenizer(attackInfo, "-");
      name = st.nextToken().trim();
      desc = st.nextToken().trim();
    } else {
      name = attackInfo;
      desc = "";
    }
    damage = attackDamage;
  }
}
