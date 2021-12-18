package battleground.entities.weapons;


/**
 * Flails is a type of weapon a player can use. It can have a damage value between 8-12 units.
 */
public class Flails extends AbstractWeapon {


  /**
   * Constructor for Flails.
   */
  public Flails() {
    this.name = "Flails";
    this.minDamageValue = 8;
    this.maxDamageValue = 12;
  }

}
