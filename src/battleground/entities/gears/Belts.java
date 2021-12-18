package battleground.entities.gears;

import battleground.entityinterfaces.Gear;
import battleground.enums.BeltSize;

/**
 * Belt clothing of the players. It comes in three sizes - Small, Medium, Large.
 */
public class Belts extends AbstractGear {

  private final BeltSize beltSize;


  /**
   * Constructor for the Belt.
   *
   * @param name name of the belt.
   * @param size name of the belt (Small, Medium, Large).
   */
  public Belts(String name, BeltSize size) {

    if (name == null || size == null) {
      throw new IllegalArgumentException("Name and size are required.");
    }

    this.beltSize = size;
    this.gearName = name;
    this.gearType = "Belt";
    this.affectStrength = 4;
    this.affectConstitution = generateConstitutionValue(size);
    this.affectDexterity = 0;
    this.affectCharisma = 0;
  }

  @Override
  public int getBeltUnitValue() {
    return this.beltSize.getUnit();
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
    return this.gearName.compareToIgnoreCase(other.gearName);
  }

  @Override
  protected int compareToFootwear(Footwear other) {
    return -1;
  }

  @Override
  public int compareTo(Gear o) {
    AbstractGear other = (AbstractGear) o;
    return -1 * other.compareToBelts(this);
  }

  @Override
  public String toString() {
    return String.format("%s (%s) - %s", this.gearName, this.gearType,
            this.beltSize.toString());
  }

  private int generateConstitutionValue(BeltSize size) {
    switch (size) {
      case SMALL:
        return -8;
      case MEDIUM:
        return 4;
      case LARGE:
        return -5;
      default:
        return 0;
    }
  }

  private String getBeltSize() {
    return this.beltSize.toString();
  }

}
