package battleground.entities.gears;

import battleground.entityinterfaces.Gear;

/**
 * Footwear gear clothing for the player.
 */
public class Footwear extends AbstractGear {

  /**
   * Constructor for the Footwear gear.
   * @param name name of the footwear.
   */
  public Footwear(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name of the footwear is required.");
    }
    this.gearName = name;
    this.gearType = "Footwear";
    this.affectStrength = 0;
    this.affectConstitution = 0;
    this.affectDexterity = 7; //Positively affect by 7 units
    this.affectCharisma = 0;
  }


  @Override
  protected int compareToHeadGear(HeadGear other) {
    return 1;
  }

  @Override
  protected int compareToPotions(Potions other) {
    return 1;
  }

  @Override
  protected int compareToBelts(Belts other) {
    return 1;
  }

  @Override
  protected int compareToFootwear(Footwear other) {
    return this.gearName.compareToIgnoreCase(other.gearName);
  }

  @Override
  public int compareTo(Gear o) {
    AbstractGear other = (AbstractGear) o;
    return -1 * other.compareToFootwear(this);
  }
}
