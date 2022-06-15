package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Tuple of two generic values.
 *
 * @author Herbert Praehofer
 *
 * @param <A> the type of the first value
 * @param <B> the type of the second value
 */
@SuppressWarnings("serial")
public class Tuple2<A, B> implements Serializable {

  /**
   * Creates a tuple of two value.
   *
   * @param <A> the type of the first value
   * @param <B> the type of the second value
   * @param v1 the first value
   * @param v2 the second value
   * @return the tuple of first and second value
   */
  public static <A, B> Tuple2<A, B> of(A v1, B v2) {
    return new Tuple2<A, B>(v1, v2);
  }

  /** The first value. */
  public final A v1;

  /** The second value. */
  public final B v2;

  /**
   * Private constructor.
   *
   * @param v1 the first value
   * @param v2 the second value
   */
  private Tuple2(A v1, B v2) {
    this.v1 = v1;
    this.v2 = v2;
  }

  /**
   * Computes a hash code from the hash codes of the two values.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
    result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
    return result;
  }

  /**
   * Compares this tuple object and the other object for equality.
   * They are equal if the second object is a Tuple2 and the two values are equal.
   *
   * @param obj the other object
   * @return {@code true} if the second object is a Tuple2 and the two values are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("unchecked")
    Tuple2<A, B> other = (Tuple2<A, B>) obj;
    if (v1 == null) {
      if (other.v1 != null)
        return false;
    } else if (!v1.equals(other.v1))
      return false;
    if (v2 == null) {
      if (other.v2 != null)
        return false;
    } else if (!v2.equals(other.v2))
      return false;
    return true;
  }

  /**
   * Returns a string representation.
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "(" + v1 + ", " + v2 + ")";
  }

}
