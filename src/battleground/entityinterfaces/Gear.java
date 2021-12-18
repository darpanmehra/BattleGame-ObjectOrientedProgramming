package battleground.entityinterfaces;

/**
 * Interface for the Gear/Clothing of the player.
 */
public interface Gear extends Comparable<Gear> {

  /**
   * Get the name of the clothing the player is wearing. (Headgear, Potions, Belts, Footwear).
   *
   * @return Gear name.
   */
  String getName();

  /**
   * Get the type of the clothing the player is wearing. (Headgear, Potions, Belts, Footwear).
   *
   * @return Gear type.
   */
  String getType();

  /**
   * Get the Strength of player affected value.
   *
   * @return affect on Strength.
   */
  int getAffectStrength();

  /**
   * Get the Constitution of player affected value.
   *
   * @return affect on Constitution.
   */
  int getAffectConstitution();

  /**
   * Get the Dexterity of player affected value.
   *
   * @return affect on Dexterity.
   */
  int getAffectDexterity();

  /**
   * Get the Charisma of player affected value.
   *
   * @return affect on Charisma.
   */
  int getAffectCharisma();

  /**
   * To string data representation of the gear.
   *
   * @return gear representation.
   */
  String toString();
}
