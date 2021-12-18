import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import battleground.entities.gears.Belts;
import battleground.entities.gears.Footwear;
import battleground.entities.gears.HeadGear;
import battleground.entities.gears.Potions;
import battleground.entityinterfaces.Gear;
import battleground.enums.BeltSize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This is a JUnit test suite to test the Gears. This tests methods such as different types of gear
 * creation, invalid gear creation, ordering of gears in the order they are worn (Headgear, potion,
 * belts, footwear), affect of the gears of the players attributes when assigned.
 */
public class GearTest {

  private Gear headgear;
  private Gear potion;
  private Gear footwear;
  private Gear beltSmall;
  private Gear beltMedium;
  private Gear beltLarge;


  @Before
  public void setUp() throws Exception {
    headgear = new HeadGear("HeadGear1");
    potion = new Potions("Potion1");
    footwear = new Footwear("Footwear1");
    beltSmall = new Belts("Belts1s", BeltSize.SMALL);
    beltMedium = new Belts("Belts1m", BeltSize.MEDIUM);
    beltLarge = new Belts("Belts1l", BeltSize.LARGE);
  }

  @Test
  public void testHeadGearName() {
    assertEquals("HeadGear1", headgear.getName());
  }

  @Test
  public void testFootwearName() {
    assertEquals("Footwear1", footwear.getName());
  }

  @Test
  public void testPotionName() {
    assertEquals("Potion1", potion.getName());
  }

  @Test
  public void testBeltName() {
    assertEquals("Belts1s", beltSmall.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectHeadGearCreation() {
    headgear = new HeadGear(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectFootwearCreation() {
    footwear = new Footwear(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectPotionCreation() {
    potion = new Potions(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectBeltCreation() {
    beltSmall = new Belts(null, null);
  }

  @Test
  public void gearOrdering() {
    List<Gear> gears = new ArrayList();
    gears.add(potion);
    gears.add(footwear);
    gears.add(headgear);
    gears.add(beltLarge);
    gears.add(beltMedium);
    gears.add(beltSmall);

    List<Gear> expected = new ArrayList<>();
    expected.add(headgear);
    expected.add(potion);
    expected.add(beltLarge);
    expected.add(beltMedium);
    expected.add(beltSmall);
    expected.add(footwear);

    Collections.sort(gears);
    assertEquals(expected, gears);
  }

  @Test
  public void testAffectStrength() {
    assertEquals(4, beltLarge.getAffectStrength());
  }

  @Test
  public void testAffectConstitution() {
    assertEquals(-5, beltLarge.getAffectConstitution());
  }

  @Test
  public void testAffectDexterity() {
    assertEquals(7, footwear.getAffectDexterity());
  }

  @Test
  public void testAffectCharisma() {
    assertNotEquals(4, beltLarge.getAffectCharisma());
  }

  @Test
  public void testSymmetryBetweenGears() {
    List<Gear> gears = new ArrayList();
    gears.add(beltSmall);
    gears.add(beltLarge);

    List<Gear> expected = new ArrayList();
    expected.add(beltLarge);
    expected.add(beltSmall);

    List<Gear> notExpected = new ArrayList();
    notExpected.add(beltSmall);
    notExpected.add(beltLarge);

    Collections.sort(gears);
    assertEquals(expected, gears);
    assertNotEquals(notExpected, gears);
  }

}
