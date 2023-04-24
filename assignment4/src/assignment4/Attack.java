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

  // setter methods

  public void setName(String name) {
    this.name = name;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setDamage(String damage) {
    this.damage = damage;
  }

  // equals (mostly used in indexOf in Driver)
  // parameter is an object to compare to
  // returns if equal or not
  public boolean equals(Object o) {
    Attack other = (Attack) o;
    return this.name.equals(other.getName()) &&
        this.desc.equals(other.getDesc()) &&
        this.damage.equals(other.getDamage());
  }

  // checks if an attack info string is valid (used before creating an Attack object)
  // parameter is an info string to check
  // returns if valid or not
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
