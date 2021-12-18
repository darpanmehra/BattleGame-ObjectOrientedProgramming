package battleground.entities.weapons;

import battleground.entityinterfaces.Weapon;
import battleground.enums.SwordType;

/**
 * Abstract method for the weapons. Contains the common methods of the weapons.
 */
public abstract class AbstractWeapon implements Weapon {

  protected String name;
  protected int minDamageValue;
  protected int maxDamageValue;

  /**
   * Get minimum damage value for the weapon.
   *
   * @return minimum damage value.
   */
  @Override
  public int getMinDamageValue() {
    return this.minDamageValue;
  }

  /**
   * Get maximum damage value for the weapon.
   *
   * @return maximum damage value.
   */
  @Override
  public int getMaxDamageValue() {
    return this.maxDamageValue;
  }

  /**
   * Get weapon name.
   *
   * @return Weapon name.
   */
  @Override
  public String getWeaponName() {
    return this.name;
  }

  protected SwordType getSwordType() {
    return null;
  }
}
