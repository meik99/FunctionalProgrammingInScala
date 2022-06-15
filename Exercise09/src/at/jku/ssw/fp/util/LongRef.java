package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Class for objects with a single mutable long field.
 *
 * @author Herbert Praehofer
 *
 */
@SuppressWarnings("serial")
public class LongRef implements Serializable {

  /**
   * Creates an object with a mutable long value.
   *
   * @param value the initial value for the mutable field
   * @return the object with the mutable long field
   */
  public static LongRef of(long value) {
    return new LongRef(value);
  }

  /** The mutable long field */
  public long value;

  /**
   * Private constructor with the value for the mutable long field.
   *
   * @param value the value for the mutable long field
   */
  private LongRef(long value) {
    this.value = value;
  }

  /**
   * Returns a string representation.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "LongRef[" + value + "]";
  }

}
