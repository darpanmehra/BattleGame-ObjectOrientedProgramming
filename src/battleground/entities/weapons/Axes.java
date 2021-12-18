package battleground.entities.weapons;

/**
 * Axes is a type of weapon a player can use. It can have a damage value between 6-10 units.
 */
public class Axes extends AbstractWeapon {

  /**
   * Constructor for creating Axes weapon in the game.
   */
  public Axes() {
    this.name = "Axes";
    this.minDamageValue = 6;
    this.maxDamageValue = 10;
  }

}
