package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Class for objects with a single mutable generic field.
 *
 * @author Herbert Praehofer
 *
 * @param <A> the type of the mutable value
 */
@SuppressWarnings("serial")
public class Ref<A> implements Serializable {

  /**
   * Creates an object with a mutable value.
   * @param <A> the type of the mutable value
   * @param value the initial value for the mutable field
   * @return the object with the mutable field
   */
  public static <A> Ref<A> of(A value) {
    return new Ref<A>(value);
  }

  /** The mutable field */
  public A value;

  /**
   * Private constructor with the value for the mutable field.
   * @param value the value for the mutable field
   */
  private Ref(A value) {
    this.value = value;
  }

  /**
   * Returns a string representation.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "Ref[" + value + "]";
  }

}
