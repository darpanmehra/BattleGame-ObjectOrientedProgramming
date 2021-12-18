import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import battleground.random.RandomMock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a unit test suite for the Random number generator implementation.
 */
public class RandomTest {

  private RandomMock randomMock;
  private Random random;


  @Before
  public void setUp() throws Exception {
    random = new Random();
    randomMock = new RandomMock(5, 4, 3, 2, 1);
  }

  @Test
  public void testRandomMock() {
    assertEquals(5, randomMock.nextInt(1));
    assertEquals(4, randomMock.nextInt(1));
    assertEquals(3, randomMock.nextInt(1));
    assertEquals(2, randomMock.nextInt(1));
    assertEquals(1, randomMock.nextInt(1));
    assertEquals(5, randomMock.nextInt(1));
    assertEquals(4, randomMock.nextInt(1));
    assertEquals(3, randomMock.nextInt(1));
    assertEquals(2, randomMock.nextInt(1));
    assertEquals(1, randomMock.nextInt(1));
  }

  @Test
  public void testRandomNumber() {
    //Two consecutive random number generated by the Random Interface will never be same as per java
    //doc.
    int randomNumber1 = random.nextInt();
    int randomNumber2 = random.nextInt();
    assertTrue(randomNumber1 != randomNumber2);
  }
}