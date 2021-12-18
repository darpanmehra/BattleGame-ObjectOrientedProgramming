package battleground.entities.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import battleground.entities.gears.Belts;
import battleground.entities.weapons.Flails;
import battleground.entities.weapons.TwoHandedSword;
import battleground.entityinterfaces.BattlePlayer;
import battleground.entityinterfaces.Gear;
import battleground.entityinterfaces.Weapon;


/**
 * Player is the entity which is playing the battle.
 */
public class Player implements BattlePlayer {

  private final Random random;

  private final int DICE_MIN = 1;
  private final int DICE_MAX = 6;
  private final String name;
  private double health;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private int strikingPower;
  private int avoidanceAbility;
  private List<Gear> gears;
  private Weapon weapon;
  private int numberWeapons;
  private final int MAX_BELTS_UNITS;

  /**
   * PLayer constructor to create a player in the game.
   *
   * @param name   name of the player.
   * @param random random object for random value generation.
   */
  public Player(String name, Random random) {
    if (name == null || random == null) {
      throw new IllegalArgumentException("Name or Random object cannot be null");
    }
    this.random = random;
    this.name = name;
    this.strength = generateAbilityValue(DICE_MIN, DICE_MAX);
    this.constitution = generateAbilityValue(DICE_MIN, DICE_MAX);
    this.dexterity = generateAbilityValue(DICE_MIN, DICE_MAX);
    this.charisma = generateAbilityValue(DICE_MIN, DICE_MAX);
    this.health = generateHealth();
    this.strikingPower = 0;
    this.avoidanceAbility = 0;
    this.MAX_BELTS_UNITS = 10; //Max 10 units of belts can be worn.
    this.gears = new ArrayList<Gear>(); //Gears the player will pick up.
    this.numberWeapons = 0; //Assign 0 weapons.
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getAbilities() {
    return String.format("Strength: %d, Constitution: %d"
                    + ", Dexterity: %d, Charisma: %d.",
            this.strength, this.constitution, this.dexterity, this.charisma);
  }

  @Override
  public void setStrength(int newStrength) {
    this.strength = newStrength;
  }

  @Override
  public int getStrength() {
    return this.strength;
  }

  @Override
  public void setConstitution(int newConstitution) {
    this.constitution = newConstitution;
  }

  @Override
  public int getConstitution() {
    return this.constitution;
  }

  @Override
  public void setDexterity(int newDexterity) {
    this.dexterity = newDexterity;
  }

  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  @Override
  public void setCharisma(int newCharisma) {
    this.charisma = newCharisma;
  }

  @Override
  public int getCharisma() {
    return this.charisma;
  }

  @Override
  public void setStrikingPower(int power) {
    this.strikingPower = power;
  }

  @Override
  public int getStrikingPower() {
    return this.strikingPower;
  }

  @Override
  public void setAvoidanceAbility(int avoidanceAbility) {
    this.avoidanceAbility = avoidanceAbility;
  }

  @Override
  public int getAvoidanceAbility() {
    return this.avoidanceAbility;
  }

  @Override
  public void setHealth(double newHealth) {
    this.health = newHealth;
  }

  @Override
  public double getHealth() {
    return this.health;
  }

  @Override
  public String toString() {
    return String.format("Name: %s \n Health: %.0f, Strength: %d, Constitution: %d"
                    + ", Dexterity: %d, Charisma: %d.", this.name, this.health,
            this.strength, this.constitution, this.dexterity, this.charisma);
  }

  @Override
  public void addGear(List<Gear> playerGearList) {
    if (this.gears.size() >= 20) {
      throw new IllegalArgumentException("Cannot add gear as the maximum limit reached.");
    }
    this.gears = filterGears(playerGearList);
    this.strikingPower = generateStrikingPower(this.gears);
    this.avoidanceAbility = generateAvoidanceAbility(this.gears);
    adjustAbilities(this.gears);
  }

  @Override
  public List<Gear> getGears() {
    Collections.sort(this.gears);
    return this.gears;
  }

  @Override
  public Weapon getWeapon() {
    return this.weapon;
  }

  @Override
  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public void setNumberWeapons(int no) {
    this.numberWeapons = no;
  }

  @Override
  public int getNumberWeapons() {
    return this.numberWeapons;
  }

  @Override
  public String getDetailedInfo() {
    Collections.sort(this.gears);
    return String.format("Name: %s \n Health: %.0f, Strength: %d, Constitution: %d"
                    + ", Dexterity: %d, Charisma: %d\n Weapon: %s, Number of weapon: %d\n "
                    + "Gears worn: %s \n Striking Power: %d \n Avoidance Ability: %s",
            this.name, this.health, this.strength, this.constitution, this.dexterity, this.charisma,
            this.weapon.getWeaponName(), this.numberWeapons, this.gears,
            this.strikingPower, this.avoidanceAbility);
  }

  @Override
  public double getWeaponDamage() {
    double numberWeapons = this.numberWeapons;
    int minDamage = this.weapon.getMinDamageValue();
    int maxDamage = this.weapon.getMaxDamageValue();
    //Check if the players have enough abilities to hold a certain type of weapon.
    if (this.weapon instanceof Flails && this.dexterity < 14) {
      numberWeapons *= 0.5;
    }
    if (this.weapon instanceof TwoHandedSword && this.strength < 14) {
      numberWeapons *= 0.5;
    }
    double value = (random.nextInt((maxDamage - minDamage)) + minDamage) * numberWeapons;
    return value;
  }

  //Private Methods

  private int generateAbilityValue(int min, int max) {
    List<Integer> values = new ArrayList<>();

    while (values.size() < 4) {
      int value = random.nextInt(max - min) + min;
      if (value != 1) {
        values.add(value);
      }
    }
    Collections.sort(values);
    return values.get(3) + values.get(2) + values.get(1);
  }

  private double generateHealth() {
    return this.charisma + this.dexterity + this.constitution + this.strength;
  }

  private int generateAvoidanceAbility(List<Gear> gears) {
    int valueCalc = 0;
    for (Gear g : gears) {
      valueCalc += g.getAffectDexterity();
    }
    return this.dexterity + valueCalc + (random.nextInt(6) + 1);
  }

  private int generateStrikingPower(List<Gear> gears) {
    int valueCalc = 0;
    for (Gear g : gears) {
      valueCalc += g.getAffectStrength();
    }
    return this.strength + valueCalc + (random.nextInt(6) + 1);
  }

  private List<Gear> filterGears(List<Gear> gears) {
    List<Gear> playerListGears = new ArrayList<>();
    int playerBeltUnitsRemaining = this.MAX_BELTS_UNITS;
    boolean isHeadGearAssigned = false;
    boolean isFootwearAssigned = false;

    for (Gear g : gears) {
      switch (g.getType()) {
        case "HeadGear":
          if (!isHeadGearAssigned) {
            playerListGears.add(g);
            isHeadGearAssigned = true;
          }
          break;
        case "Footwear":
          if (!isFootwearAssigned) {
            playerListGears.add(g);
            isFootwearAssigned = true;
          }
          break;
        case "Belt":
          Belts belt = (Belts) g;
          if (playerBeltUnitsRemaining >= belt.getBeltUnitValue()) {
            playerListGears.add(g);
            playerBeltUnitsRemaining -= belt.getBeltUnitValue();
          }
          break;
        default:
          playerListGears.add(g);
          break;
      }
    }
    return playerListGears;
  }

  private void adjustAbilities(List<Gear> gears) {
    int valueStrength = 0;
    int valueConstitution = 0;
    int valueDexterity = 0;
    int valueCharisma = 0;
    for (Gear gear : gears) {
      valueStrength += gear.getAffectStrength();
      valueConstitution += gear.getAffectConstitution();
      valueDexterity += gear.getAffectDexterity();
      valueCharisma += gear.getAffectCharisma();
    }
    this.strength += valueStrength;
    this.constitution += valueConstitution;
    this.dexterity += valueDexterity;
    this.charisma += valueCharisma;
  }
}
