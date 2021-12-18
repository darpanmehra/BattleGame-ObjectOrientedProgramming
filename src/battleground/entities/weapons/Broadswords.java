package battleground.entities.weapons;

import battleground.enums.SwordType;

/**
 * Board sword type of sword.
 */
public class Broadswords extends AbstractWeapon {

  private final SwordType type;

  /**
   * Constructor for the Broadswords weapon.
   */
  public Broadswords() {
    this.name = "Sword";
    this.type = SwordType.BROADSWORDS;
    this.minDamageValue = type.getMinDamage();
    this.maxDamageValue = type.getMaxDamage();
  }

  @Override
  public SwordType getSwordType() {
    return this.type;
  }

  @Override
  public String getWeaponName() {
    return String.format("%s (%s)", this.name, this.type.getTypeName());
  }
}
