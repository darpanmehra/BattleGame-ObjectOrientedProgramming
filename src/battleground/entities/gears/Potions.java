package battleground.entities.gears;

import battleground.entityinterfaces.Gear;

/**
 * Potions for the player.
 */
public class Potions extends AbstractGear {

  /**
   * Constructor for the Potions gear.
   * @param name name of the Potions.
   */
  public Potions(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name of the Potion is required.");
    }
    this.gearName = name;
    this.gearType = "Potion";
    this.affectStrength = -2; //Negatively affect by 8 units.
    this.affectConstitution = 0;
    this.affectDexterity = 0;
    this.affectCharisma = 0;
  }


  @Override
  protected int compareToHeadGear(HeadGear other) {
    return 1;
  }

  @Override
  protected int compareToPotions(Potions other) {
    return this.gearName.compareToIgnoreCase(other.gearName);
  }

  @Override
  protected int compareToBelts(Belts other) {
    return -1;
  }

  @Override
  protected int compareToFootwear(Footwear other) {
    return -1;
  }

  @Override
  public int compareTo(Gear o) {
    AbstractGear other = (AbstractGear) o;
    return -1 * other.compareToPotions(this);
  }

}
