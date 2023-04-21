package assignment4;

import java.util.StringTokenizer;

public class Attack {
  private String name;
  private String desc;
  private String damage;

  // getter methods
  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public String getDamage() {
    return damage;
  }

  public static boolean isValid(String attackInfo) {
    if (attackInfo.contains("-") || attackInfo.contains("–")) {
      StringTokenizer st = new StringTokenizer(attackInfo, "-–");
      if (st.countTokens() != 2) return false;
      String name = st.nextToken().trim();
      String desc = st.nextToken().trim();
      if (name.length() == 0 || desc.length() == 0) return false;
      return true;
    } else {
      return false;
    }
  }

  // constructor splits up the name and description from the given info
  public Attack(String attackInfo, String attackDamage) {
    if (attackInfo.contains("-") || attackInfo.contains("–")) {
      StringTokenizer st = new StringTokenizer(attackInfo, "-–");
      name = st.nextToken().trim();
      desc = st.nextToken().trim();
    } else {
      name = attackInfo;
      desc = "";
    }
    damage = attackDamage;
  }
}
