package battleground.enums;

/**
 * Sizes of the belts.
 */
public enum BeltSize {
  SMALL(1), MEDIUM(2), LARGE(4);

  private final int value;

  BeltSize(final int unit) {
    value = unit;
  }

  /**
   * Unit for the belt as per the size.
   *
   * @return the unit value.
   */
  public int getUnit() {
    return value;
  }

}
