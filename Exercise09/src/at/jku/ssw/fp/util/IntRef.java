package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Class for objects with a single mutable int field.
 *
 * @author Herbert Praehofer
 *
 */
@SuppressWarnings("serial")
public class IntRef implements Serializable {

  /**
   * Creates an object with a mutable int value.
   * @param value the initial value for the mutable field
   * @return the object with the mutable int field
   */
  public static IntRef of(int value) {
    return new IntRef(value);
  }

  /** The mutable int field */
  public int value;

  /**
   * Private constructor with the value for the mutable int field.
   *
   * @param value the value for the mutable int field
   */
  private IntRef(int value) {
    this.value = value;
  }

  /**
   * Returns a string representation.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "IntRef[" + value + "]";
  }

}
