import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import battleground.entities.gears.Belts;
import battleground.entities.gears.Footwear;
import battleground.entities.gears.HeadGear;
import battleground.entities.gears.Potions;
import battleground.entities.players.Player;
import battleground.entityinterfaces.Gear;
import battleground.enums.BeltSize;
import battleground.random.RandomMock;

import static org.junit.Assert.assertEquals;

/**
 * This is a unit test for the the Player class. This tests various methods related to the player
 * object. This includes tests for creating a player object, invalid creation of player object,
 * abilities of the player, adding gears to the player, maximum units of belts allowed, only 1
 * headgear and footwear allowed and ordering of gears,
 */
public class PlayerTest {

  private Player player;
  private List<Gear> bag;
  private Random random;

  @Before
  public void setUp() throws Exception {
    random = new RandomMock(5, 4, 3, 2, 1);
    this.player = new Player("Player1", random);
    bag = new ArrayList<>();
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
  }

  @Test
  public void getName() {
    assertEquals("Player1", player.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getInvalidName() {
    this.player = new Player(null, random);
    assertEquals("Player1", player.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createInvalidPlayer() {
    this.player = new Player("Player1", null);
    assertEquals("Player1", player.getName());
  }

  @Test
  public void getStrength() {
    assertEquals(15, player.getStrength());
  }

  @Test
  public void getConstitution() {
    assertEquals(15, player.getStrength());
  }

  @Test
  public void getDexterity() {
    assertEquals(15, player.getStrength());
  }

  @Test
  public void getCharisma() {
    assertEquals(15, player.getStrength());
  }

  @Test
  public void testAddGears() {
    random = new RandomMock(24, 54, 63, 72, 68);
    List<Gear> playerGearList = new ArrayList<>();
    while (playerGearList.size() != 20) {
      int pickValue = random.nextInt(bag.size() - 1);
      playerGearList.add(this.bag.get(pickValue));
      this.bag.remove(pickValue);
    }
    player.addGear(playerGearList);

    assertEquals(13, player.getGears().size());
  }

  @Test
  public void testOnlyOneHeadGear() {
    int headGearNumber = 0;
    random = new RandomMock(24, 54, 63, 72, 68);
    List<Gear> playerGearList = new ArrayList<>();
    while (playerGearList.size() != 20) {
      int pickValue = random.nextInt(bag.size() - 1);
      playerGearList.add(this.bag.get(pickValue));
      this.bag.remove(pickValue);
    }
    player.addGear(playerGearList);

    for (Gear gear : player.getGears()) {
      if (gear.getType().equals("HeadGear")) {
        headGearNumber += 1;
      }
    }
    assertEquals(1, headGearNumber);
  }

  @Test
  public void testOnlyOneFootwear() {
    int footwearNumber = 0;
    random = new RandomMock(24, 54, 63, 72, 68);
    List<Gear> playerGearList = new ArrayList<>();
    while (playerGearList.size() != 20) {
      int pickValue = random.nextInt(bag.size() - 1);
      playerGearList.add(this.bag.get(pickValue));
      this.bag.remove(pickValue);
    }
    player.addGear(playerGearList);

    for (Gear gear : player.getGears()) {
      if (gear.getType().equals("Footwear")) {
        footwearNumber += 1;
      }
    }
    assertEquals(1, footwearNumber);
  }

  @Test
  public void testMaxBeltUnits() {
    int beltUnits = 0;
    random = new RandomMock(24, 54, 63, 72, 68);
    List<Gear> playerGearList = new ArrayList<>();
    while (playerGearList.size() != 20) {
      int pickValue = random.nextInt(bag.size() - 1);
      playerGearList.add(this.bag.get(pickValue));
      this.bag.remove(pickValue);
    }
    player.addGear(playerGearList);

    for (Gear gear : player.getGears()) {
      if (gear.getType().equals("Belt")) {
        Belts belt = (Belts) gear;
        beltUnits += belt.getBeltUnitValue();
      }
    }
    assertEquals(9, beltUnits);
  }

}