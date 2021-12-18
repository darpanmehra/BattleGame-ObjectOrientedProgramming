package battleground.entityinterfaces;

import java.util.List;

/**
 * Interface for a player of battle.
 */
public interface BattlePlayer {

  /**
   * Get the name of the player.
   *
   * @return name of player.
   */
  String getName();

  /**
   * Get the abilities of the player which includes information about Strength, Constitution,
   * Dexterity, Charisma.
   *
   * @return abilities of the player.
   */
  String getAbilities();

  /**
   * Set the Strength of the player.
   */
  void setStrength(int newStrength);

  /**
   * Get the Strength of the player.
   *
   * @return Strength value of the player.
   */
  int getStrength();

  /**
   * Set the Constitution of the player.
   */
  void setConstitution(int newConstitution);

  /**
   * Get the Constitution of the player.
   *
   * @return Constitution value of the player.
   */
  int getConstitution();

  /**
   * Set the Dexterity of the player.
   */
  void setDexterity(int newDexterity);

  /**
   * Get the Dexterity of the player.
   *
   * @return Dexterity value of the player.
   */
  int getDexterity();

  /**
   * Set the Charisma of the player.
   */
  void setCharisma(int newCharisma);

  /**
   * Get the Charisma of the player.
   *
   * @return Charisma value of the player.
   */
  int getCharisma();

  /**
   * Set the striking power of the player.
   */
  void setStrikingPower(int power);

  /**
   * Get the striking power of the player.
   *
   * @return Striking power value of the player.
   */
  int getStrikingPower();

  /**
   * Set the Avoidance Ability of the player.
   */
  void setAvoidanceAbility(int avoidanceAbility);

  /**
   * Get the Avoidance Ability of the player.
   *
   * @return Avoidance Ability value of the player.
   */
  int getAvoidanceAbility();

  /**
   * Set the health of the player.
   */
  void setHealth(double newHealth);

  /**
   * Get the health of the player.
   *
   * @return Health value of the player.
   */
  double getHealth();

  /**
   * Information representation of the player.
   *
   * @return information of the player.
   */
  String toString();

  /**
   * Add the gears picked from the bag to the player.
   *
   * @param gears gears to be added.
   */
  void addGear(List<Gear> gears);

  /**
   * List of gears assigned to the players.
   */
  List<Gear> getGears();

  /**
   * Get the weapon assigned to the player.
   *
   * @return weapon assigned.
   */
  Weapon getWeapon();

  /**
   * Assign a weapon to a player.
   */
  void setWeapon(Weapon weapon);

  /**
   * Set the number of weapons assigned to the player.
   */
  void setNumberWeapons(int no);

  /**
   * Get the number of weapons assigned to the player.
   *
   * @return number of weapons assigned.
   */
  int getNumberWeapons();

  /**
   * Get the detailed information about the player. Including their new abilities, weapons they have
   * and health.
   *
   * @return Detailed info of the player.
   */
  String getDetailedInfo();

  /**
   * Get the damage of the weapon the player is having.
   *
   * @return weapon damage.
   */
  double getWeaponDamage();
}
