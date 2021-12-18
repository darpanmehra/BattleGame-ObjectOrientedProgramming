package battleground.entities.gears;

import battleground.entityinterfaces.Gear;

/**
 * Head gear clothing for the player.
 */
public class HeadGear extends AbstractGear {

  /**
   * Constructor for the HeadGear gear.
   *
   * @param name name of the HeadGear.
   */
  public HeadGear(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name of the Head Gear is required.");
    }
    this.gearName = name;
    this.gearType = "HeadGear";
    this.affectStrength = 0;
    this.affectConstitution = 15; //Positively affect by 15 units.
    this.affectDexterity = 0;
    this.affectCharisma = 0;
  }

  @Override
  protected int compareToHeadGear(HeadGear other) {
    return this.gearName.compareToIgnoreCase(other.gearName);
  }

  @Override
  protected int compareToPotions(Potions other) {
    return -1;
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
    return -1 * other.compareToHeadGear(this);
  }

}
