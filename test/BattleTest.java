import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import battleground.Battle;
import battleground.entities.players.Player;
import battleground.random.RandomMock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit test suite for testing the Battle class. This suite test methods such as starting
 * the battle with the required number of players, gear bag creating, ability for an opponent to
 * pick 20 random gears from the bag, ability of the player to pick a weapon from the armory, basic
 * and detailed information about the players in the battle, determining the first strike between
 * both players.
 */
public class BattleTest {

  private Battle battle;
  private Random random;

  @Before
  public void setUp() throws Exception {
    random = new RandomMock(5, 4, 3, 2, 1);
    battle = new Battle(2, random);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startBattleWithWrongNumberOfPlayers() {
    battle = new Battle(1, random);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startBattleWithNoPlayers() {
    battle = new Battle(0, random);
  }

  @Test
  public void testGearBagGeneration() {
    assertEquals(100, battle.getNumberOfGearsInBag());
  }

  @Test
  public void testPlayerGeneration() {
    Player attacker = battle.getInitialOrderOfFight().get(0);
    assertEquals(12, attacker.getStrength());

    Player opponent = battle.getInitialOrderOfFight().get(0);
    assertEquals(12, opponent.getStrength());
  }

  @Test
  public void testPickGearsForPlayers() {
    battle.pickGearForPlayers();
    //20 gears for each player is drawn form bag. Left over = 60.
    assertEquals(60, battle.getNumberOfGearsInBag());
  }

  @Test
  public void testPickWeaponFromArmory() {
    //Picking weapon for player 1
    battle.pickWeapon(1);
    //Picking weapon for player 2
    battle.pickWeapon(2);
    Player player1 = battle.getInitialOrderOfFight().get(0);
    Player player2 = battle.getInitialOrderOfFight().get(1);

    assertEquals("Sword (TwoHandedSword)", player1.getWeapon().getWeaponName());
    assertEquals("Sword (Broadswords)", player2.getWeapon().getWeaponName());
  }

  @Test
  public void testPlayerBasicInfoDisplay() {
    String expected = "Name: Player1 \n" +
            " Health: 57, Strength: 15, Constitution: 15, Dexterity: 14, Charisma: 13.\n" +
            "Name: Player2 \n" +
            " Health: 56, Strength: 12, Constitution: 15, Dexterity: 15, Charisma: 14.\n";

    assertEquals(expected, battle.getPlayersBasicInfo());
  }

  @Test
  public void testPlayerDetailedInfoDisplay() {
    battle.pickGearForPlayers();
    //Picking weapon for player 1
    battle.pickWeapon(1);
    //Picking weapon for player 2
    battle.pickWeapon(2);

    String expected = "Name: Player1 \n" +
            " Health: 57, Strength: 15, Constitution: 15, Dexterity: 21, Charisma: 13\n" +
            " Weapon: Sword (Katanas), Number of weapon: 1\n" +
            " Gears worn: [Footwear4 (Footwear)] \n" +
            " Striking Power: 19 \n" +
            " Avoidance Ability: 24\n" +
            "\n" +
            "Name: Player2 \n" +
            " Health: 56, Strength: 12, Constitution: 30, Dexterity: 22, Charisma: 14\n" +
            " Weapon: Sword (TwoHandedSword), Number of weapon: 1\n" +
            " Gears worn: [Headgear2 (HeadGear), Footwear20 (Footwear)] \n" +
            " Striking Power: 14 \n" +
            " Avoidance Ability: 28\n" +
            "\n";

    assertEquals(expected, battle.getPlayersDetailedInfo());
  }

  @Test
  public void testFirstStrike() {
    Player attacker = battle.getInitialOrderOfFight().get(0);
    assertEquals("Player2", attacker.getName());
  }

  @Test
  public void testFirstStrikeCharismaCheck() {
    Player attacker = battle.getInitialOrderOfFight().get(0);
    Player opponent = battle.getInitialOrderOfFight().get(1);

    //Test if the attackers charisma is always greater than opponent's charisma.
    assertTrue(attacker.getCharisma() >= opponent.getCharisma());
  }

  @Test
  public void bagWith25percentDiminishingGears() {
    int numberOfGears = battle.getGearsWithDiminishingPower();
    assertEquals(numberOfGears, 0.25 * battle.getNumberOfGearsInBag(), 1);
  }

  @Test
  public void testBattleOutcome() {
    random = new RandomMock(5, 4, 3, 2, 1);
    boolean outcome = false;
    battle = new Battle(2, random);
    battle.pickGearForPlayers();
    battle.pickWeapon(1);
    battle.pickWeapon(2);

    List<Player> players = battle.getInitialOrderOfFight();
    Player attacker;
    Player opponent;

    for (int i = 1; i <= 50; i++) {
      attacker = players.get(0);
      opponent = players.get(1);
      battle.roundAction(attacker, opponent);
      //Check if Battle is over. If not change the attacker and opponent.
      if (battle.isBattleOver()) {
        //if player wins, it is a outcome of the battle
        outcome = true;
      } else {
        if (i == 50) {
          //If the battle is drawn, it is a outcome of the battle too.
          outcome = true;
        } else {
          Collections.swap(players, 0, 1);
        }
      }
    }
    //Test if the battle has an outcome.
    assertTrue(outcome);
  }

}
