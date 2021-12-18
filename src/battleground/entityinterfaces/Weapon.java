package battleground.entityinterfaces;

/**
 * Interface for the weapons used in the Battle.
 */
public interface Weapon {

  /**
   * Get minimum damage value for the weapon.
   *
   * @return minimum damage value.
   */
  int getMinDamageValue();

  /**
   * Get maximum damage value for the weapon.
   *
   * @return maximum damage value.
   */
  int getMaxDamageValue();

  /**
   * Get weapon name.
   *
   * @return Weapon name.
   */
  String getWeaponName();

}
