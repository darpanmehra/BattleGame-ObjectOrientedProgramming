package battleground.enums;

/**
 * Type of the sword and its min-max damage value.
 */
public enum SwordType {
  KATANAS("Katanas", 4, 6),
  BROADSWORDS("Broadswords", 6, 10),
  TWOHANDEDSWORDS("TwoHandedSword", 8, 12);

  private final String name;
  private final int minDamage;
  private final int maxDamage;

  SwordType(String name, int minDamage, int maxDamage) {
    this.name = name;
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
  }

  public String getTypeName() {
    return this.name;
  }

  public int getMinDamage() {
    return this.minDamage;
  }

  public int getMaxDamage() {
    return this.maxDamage;
  }
}
