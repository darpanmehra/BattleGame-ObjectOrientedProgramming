package battleground.entities.weapons;

import battleground.enums.SwordType;


/**
 * Katanas type of sword.
 */
public class Katanas extends AbstractWeapon {

  private final SwordType type;

  /**
   * Constructor for the katanas sword weapon.
   *
   */
  public Katanas() {
    this.name = "Sword";
    this.type = SwordType.KATANAS;
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
