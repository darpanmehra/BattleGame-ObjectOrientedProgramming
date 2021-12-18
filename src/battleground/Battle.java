package battleground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import battleground.entities.gears.Belts;
import battleground.entities.gears.Footwear;
import battleground.entities.gears.HeadGear;
import battleground.entities.gears.Potions;
import battleground.entities.players.Player;
import battleground.entities.weapons.Axes;
import battleground.entities.weapons.Broadswords;
import battleground.entities.weapons.Flails;
import battleground.entities.weapons.Katanas;
import battleground.entities.weapons.TwoHandedSword;
import battleground.entityinterfaces.Gear;
import battleground.entityinterfaces.Weapon;
import battleground.enums.BeltSize;

/**
 * This is a facade for the battle. It contains all the methods for various actions/ tasks performed
 * in the battle. This class controls all the functions required by the battle such as creating a
 * bag of gears, assigning gears to the players, assigning a weapon to the player, selecting the
 * first strike, the rounds of the battle, etc.
 */
public class Battle {

  private final List<Player> players;
  private boolean isBattleOver;
  private List<Gear> gearBag;
  private final Random random;

  /**
   * Constructor for thr Battle.
   *
   * @param numberOfPlayers number of players to start the battle. Should be 2.
   * @param random          random class for the random number generation.
   */
  public Battle(int numberOfPlayers, Random random) {

    this.random = random;

    if (numberOfPlayers != 2) {
      throw new IllegalArgumentException("Number of players should be 2");
    }

    //Generate Players
    players = new ArrayList<>(2);
    for (int i = 1; i <= numberOfPlayers; i++) {
      Player player = new Player("Player" + i, random);
      players.add(player);
    }

    //Generate bag
    gearBag = new ArrayList<>();
    gearBag = generateGearBag();

    isBattleOver = false;
  }

  /**
   * Get the basic information of the players in the battle.
   *
   * @return basic information of the player.
   */
  public String getPlayersBasicInfo() {
    if (players.size() != 2) {
      throw new IllegalStateException("There should be 2 players in the battle.");
    }
    StringBuilder sb = new StringBuilder();
    for (Player player : players) {
      sb.append(player.toString()).append("\n");
    }
    return sb.toString();
  }

  /**
   * Get the detailed information of the player in the battle field including the gears and weapon.
   *
   * @return detailed information of the player in the battle field.
   */
  public String getPlayersDetailedInfo() {
    if (players.size() != 2) {
      throw new IllegalStateException("There should be 2 players in the battle.");
    }
    StringBuilder sb = new StringBuilder();
    for (Player player : players) {
      sb.append(player.getDetailedInfo()).append("\n\n");
    }
    return sb.toString();
  }

  /**
   * Get the bag of gears.
   *
   * @return bag of gears in sorted order from top to bottom- Headgear, Potion, Belt, Footwear.
   */
  public String getGearBag() {
    List<Gear> tempBag = new ArrayList<>(this.gearBag);
    Collections.sort(tempBag);
    return tempBag.toString();
  }

  /**
   * Pick the gears randomly for the players from the bag of gears.
   */
  public void pickGearForPlayers() {
    if (players.size() != 2) {
      throw new IllegalStateException("Battle does not have sufficient players");
    }
    for (Player player : players) {
      List<Gear> playerGearList = new ArrayList<>();
      while (playerGearList.size() != 20) {
        int pickValue = randomValuePickHelper(0, gearBag.size() - 1);
        playerGearList.add(this.gearBag.get(pickValue));
        this.gearBag.remove(pickValue);
      }
      player.addGear(playerGearList);
    }
  }

  /**
   * Pick weapon for the player.
   *
   * @param playerNumber player number to pick the weapon for.
   */
  public void pickWeapon(int playerNumber) {
    if (playerNumber < 1 || playerNumber > 2) {
      throw new IllegalArgumentException("Cannot find player");
    }
    Player player = players.get(playerNumber - 1);
    int pickValue = randomValuePickHelper(1, 5);
    Weapon weapon;
    switch (pickValue) {
      case 1:
        weapon = new Axes();
        break;
      case 2:
        weapon = new Flails();
        break;
      case 3:
        weapon = new TwoHandedSword(); //new Sword(SwordType.TWOHANDEDSWORDS);
        break;
      case 4:
        weapon = new Broadswords(); //new Sword(SwordType.BROADSWORDS);
        break;
      case 5:
        weapon = new Katanas(); //new Sword(SwordType.KATANAS);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + pickValue);
    }
    player.setWeapon(weapon);

    if (pickValue == 5 && randomValuePickHelper(1, 5) == 5) {
      player.setNumberWeapons(2);
    } else {
      player.setNumberWeapons(1);
    }
  }

  /**
   * Get the initial order of the fight.
   *
   * @return list of players where attacker is first, followed by opponent.
   */
  public List<Player> getInitialOrderOfFight() {
    if (players.size() != 2) {
      throw new IllegalStateException("There should be 2 players in the battle.");
    }
    Player p1 = players.get(0);
    Player p2 = players.get(1);

    List<Player> playersList = new ArrayList<>();
    if (p1.getCharisma() >= p2.getCharisma()) {
      playersList.add(p1);
      playersList.add(p2);
    } else {
      playersList.add(p2);
      playersList.add(p1);
    }
    return playersList;
  }

  /**
   * Get the number of gears in the bag.
   *
   * @return the number of gears in the bag.
   */
  public int getNumberOfGearsInBag() {
    return this.gearBag.size();
  }

  /**
   * Each round of the game. This method calculates the striking ability of the player, avoidance
   * ability of the player and the result of the round.
   *
   * @param attacker is the attacker of each round.
   * @param opponent is the opponent of the round.
   * @return returns information about the round such as who attacked, how much damage they caused,
   *     opponent's health, if the opponent was successful in avoiding the attack.
   */
  public String roundAction(Player attacker, Player opponent) {
    StringBuilder sb = new StringBuilder();
    int attackerStrikingPower = attacker.getStrikingPower();
    int opponentAvoidanceAbility = opponent.getAvoidanceAbility();
    double opponentHealth = opponent.getHealth();
    double potentialStrikingDamage;
    double actualDamage;

    if (attackerStrikingPower > opponentAvoidanceAbility) {
      Weapon attackerWeapon = attacker.getWeapon();

      //Calculate Potential Strike Damage
      potentialStrikingDamage = attacker.getStrength() + attacker.getWeaponDamage();

      sb.append("Weapon: ").append(attackerWeapon.getWeaponName())
              .append(", Potential Striking Damage: ").append(potentialStrikingDamage);

      //Calculate Actual Damage Value
      actualDamage = potentialStrikingDamage - (double) opponent.getConstitution();

      sb.append("\nAttacker Strikes! \n");
      sb.append("Damage taken: ").append(actualDamage).append(" \n");

      if (actualDamage > 0) {
        //Subtracted from the player's health.
        opponent.setHealth(opponentHealth - actualDamage);
        sb.append("Opponent's Health: ").append(opponent.getHealth()).append(" \n");
      } else {
        sb.append("Opponent defends successfully. Opponent's Health: ")
                .append(opponent.getHealth()).append(" \n");
      }
    } else {
      sb.append("Opponent avoids the attack! \n");
      sb.append("Opponent's Health: ").append(opponent.getHealth()).append(" \n");
    }
    //Check if the round is over
    if (opponent.getHealth() <= 0) {
      isBattleOver = true;
      sb.append("\nResult: ")
              .append(attacker.getName()).append(" Wins the battle. Congratulations!");
    }
    return sb.toString();
  }

  public boolean isBattleOver() {
    return this.isBattleOver;
  }

  /**
   * This is a method to get the number of gears with diminishing powers in the bag.
   *
   * @return number of gears with diminishing powers.
   */
  public int getGearsWithDiminishingPower() {
    int number = 0;
    for (Gear gear : gearBag) {
      if (gear.getAffectStrength() < 0 || gear.getAffectCharisma() < 0
              || gear.getAffectDexterity() < 0 || gear.getAffectConstitution() < 0) {
        number += 1;
      }
    }
    return number;
  }

  //Private methods
  private int randomValuePickHelper(int min, int max) {
    return random.nextInt(max - min) + min;
  }

  private List<Gear> generateGearBag() {
    List<Gear> bag = new ArrayList<>();

    //Footwear
    for (int i = 1; i <= 30; i++) {
      Gear footwear = new Footwear("Footwear" + i);
      bag.add(footwear);
    }
    //Headgear
    for (int i = 1; i <= 30; i++) {
      Gear headgear = new HeadGear("Headgear" + i);
      bag.add(headgear);
    }
    //Potions
    for (int i = 1; i <= 15; i++) {
      Gear potion = new Potions("Potion" + i);
      bag.add(potion);
    }
    //Small Belts
    for (int i = 1; i <= 5; i++) {
      Gear belt = new Belts("Belt" + i + "s", BeltSize.SMALL);
      bag.add(belt);
    }
    //Medium Belts
    for (int i = 1; i <= 15; i++) {
      Gear belt = new Belts("Belt" + i + "m", BeltSize.MEDIUM);
      bag.add(belt);
    }
    //Large Belts
    for (int i = 1; i <= 5; i++) {
      Gear belt = new Belts("Belt" + i + "l", BeltSize.LARGE);
      bag.add(belt);
    }
    return bag;
  }

}
