package battleground.entities.gears;


import battleground.entityinterfaces.Gear;

/**
 * Abstract method for the Gear. It has all the common methods of the gears along with comparators.
 */
public abstract class AbstractGear implements Gear {

  protected String gearName;
  protected String gearType;
  protected int affectStrength;
  protected int affectConstitution;
  protected int affectDexterity;
  protected int affectCharisma;

  /**
   * Get the name of the clothing the player is wearing. (Headgear, Potions, Belts, Footwear).
   *
   * @return Gear name.
   */
  @Override
  public String getName() {
    return this.gearName;
  }

  /**
   * Get the type of the clothing the player is wearing. (Headgear, Potions, Belts, Footwear).
   *
   * @return Gear type.
   */
  @Override
  public String getType() {
    return this.gearType;
  }

  /**
   * Get the Strength of player affected value.
   *
   * @return affect on Strength.
   */
  @Override
  public int getAffectStrength() {
    return this.affectStrength;
  }

  /**
   * Get the Constitution of player affected value.
   *
   * @return affect on Constitution.
   */
  @Override
  public int getAffectConstitution() {
    return this.affectConstitution;
  }

  /**
   * Get the Dexterity of player affected value.
   *
   * @return affect on Dexterity.
   */
  @Override
  public int getAffectDexterity() {
    return this.affectDexterity;
  }

  /**
   * Get the Charisma of player affected value.
   *
   * @return affect on Charisma.
   */
  @Override
  public int getAffectCharisma() {
    return this.affectCharisma;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", this.gearName, this.gearType);
  }

  @Override
  public int compareTo(Gear o) {
    return 0;
  }

  protected int getBeltUnitValue() {
    return 0;
  }

  protected int compareToHeadGear(HeadGear other) {
    return 0;
  }

  protected int compareToPotions(Potions other) {
    return 0;
  }

  protected int compareToBelts(Belts other) {
    return 0;
  }

  protected int compareToFootwear(Footwear other) {
    return 0;
  }

}
