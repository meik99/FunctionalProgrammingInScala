package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Class for objects with a single mutable double field.
 *
 * @author Herbert Praehofer
 *
 */
@SuppressWarnings("serial")
public class DoubleRef implements Serializable {

  /**
   * Creates an object with a mutable double value.
   *
   * @param value the initial value for the mutable field
   * @return the object with the mutable double field
   */
  public static DoubleRef of(double value) {
    return new DoubleRef(value);
  }

  /** The mutable double field */
  public double value;

  /**
   * Private constructor with the value for the mutable double field.
   *
   * @param value the value for the mutable double field
   */
  private DoubleRef(double value) {
    this.value = value;
  }

  /**
   * Returns a string representation.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "DoubleRef[" + value + "]";
  }

}
