import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import battleground.entities.players.Player;
import battleground.entities.weapons.Axes;
import battleground.entities.weapons.Broadswords;
import battleground.entities.weapons.Flails;
import battleground.entities.weapons.Katanas;
import battleground.entities.weapons.TwoHandedSword;
import battleground.entityinterfaces.Weapon;
import battleground.random.RandomMock;

import static org.junit.Assert.assertEquals;

/**
 * This is a Junit test suite to test the weapons. Tests include creation of weapons, their min and
 * max damage values, weapons constraints like Flail, Two handed Sword, Katanas.
 */
public class WeaponTest {

  private Weapon axe;
  private Weapon broadsword;
  private Weapon flails;
  private Weapon katana;
  private Weapon twoHandedSword;

  @Before
  public void setUp() throws Exception {
    axe = new Axes();
    broadsword = new Broadswords();
    flails = new Flails();
    katana = new Katanas();
    twoHandedSword = new TwoHandedSword();
  }

  @Test
  public void getMinDamageValueAxe() {
    assertEquals(6, axe.getMinDamageValue());
  }

  @Test
  public void getMaxDamageValueAxe() {
    assertEquals(10, axe.getMaxDamageValue());
  }

  @Test
  public void getMinDamageValueFlail() {
    assertEquals(8, flails.getMinDamageValue());
  }

  @Test
  public void getMaxDamageValueFlail() {
    assertEquals(12, flails.getMaxDamageValue());
  }

  @Test
  public void getMinDamageValueBroadSword() {
    assertEquals(6, broadsword.getMinDamageValue());
  }

  @Test
  public void getMaxDamageValueBroadSword() {
    assertEquals(10, broadsword.getMaxDamageValue());
  }

  @Test
  public void getMinDamageValueKatanas() {
    assertEquals(4, katana.getMinDamageValue());
  }

  @Test
  public void getMaxDamageValueKatanas() {
    assertEquals(6, katana.getMaxDamageValue());
  }

  @Test
  public void getMinDamageValueTwoHandedSword() {
    assertEquals(8, twoHandedSword.getMinDamageValue());
  }

  @Test
  public void getMaxDamageValueTwoHandedSword() {
    assertEquals(12, twoHandedSword.getMaxDamageValue());
  }

  @Test
  public void testFlailMinCriteria() {
    Random random = new RandomMock(3, 3, 3, 2, 1);
    Player player = new Player("Player1", random);
    player.setWeapon(flails);
    player.setNumberWeapons(1);
    assertEquals(5.5, player.getWeaponDamage(), 1);
  }

  @Test
  public void testTwoHandedSwordMinCriteria() {
    Random random = new RandomMock(3, 3, 3, 2, 1);
    Player player = new Player("Player1", random);
    player.setWeapon(twoHandedSword);
    player.setNumberWeapons(1);
    assertEquals(5.5, player.getWeaponDamage(), 1);
  }

  @Test
  public void testKatanasSingle() {
    Random random = new RandomMock(5, 4, 3, 2, 1);
    Player player = new Player("Player1", random);
    player.setWeapon(katana);
    player.setNumberWeapons(1);
    assertEquals(8.0, player.getWeaponDamage(), 1);
  }

  @Test
  public void testKatanasDouble() {
    Random random = new RandomMock(5, 4, 3, 2, 1);
    Player player = new Player("Player1", random);
    player.setWeapon(katana);
    player.setNumberWeapons(2);
    assertEquals(16.0, player.getWeaponDamage(), 1);
  }

}