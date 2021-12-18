package battleground.entities.weapons;

import battleground.enums.SwordType;

/**
 * Two handed swords type of sword.
 */
public class TwoHandedSword extends AbstractWeapon {

  private final SwordType type;

  /**
   * Constructor for the Two handed sword weapon.
   *
   */
  public TwoHandedSword() {
    this.name = "Sword";
    this.type = SwordType.TWOHANDEDSWORDS;
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
