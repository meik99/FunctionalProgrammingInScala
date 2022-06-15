package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Tuple of five generic values. 
 * 
 * @author Herbert Praehofer
 *
 * @param <A> the type of the first value
 * @param <B> the type of the second value 
 * @param <C> the type of the third value 
 * @param <D> the type of the fourth value 
 * @param <E> the type of the fifth value 
 */
@SuppressWarnings("serial")
public class Tuple5<A, B, C, D, E> implements Serializable {

  /**
   * Creates a tuple of five value. 
   * 
   * @param <A> the type of the first value
   * @param <B> the type of the second value 
   * @param <C> the type of the third value 
   * @param <D> the type of the fourth value 
   * @param <E> the type of the fifth value 
   * @param v1 the first value
   * @param v2 the second value
   * @param v3 the third value
   * @param v4 the fourth value
   * @param v5 the fifth value
   * @return the tuple with four values 
   */
  public static <A, B, C, D, E> Tuple5<A, B, C, D, E> of(A v1, B v2, C v3, D v4, E v5) {
    return new Tuple5<A, B, C, D, E>(v1, v2, v3, v4, v5);
  }

  /** The first value. */
  public final A v1;

  /** The second value. */
  public final B v2;

  /** The third value. */
  public final C v3;

  /** The fourth value. */
  public final D v4;

  /** The fifth value. */
  public final E v5;

  /** 
   * Private constructor. 
   * 
   * @param v1 the first value 
   * @param v2 the second value
   * @param v3 the third value
   * @param v4 the fourth value
   * @param v5 the fifth value
   */  
  private Tuple5(A v1, B v2, C v3, D v4, E v5) {
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.v5 = v5;
  }

  /** 
   * Computes a hash code from the hash codes of the five values. 
   * 
   * @return the hash code
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
    result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
    result = prime * result + ((v3 == null) ? 0 : v3.hashCode());
    result = prime * result + ((v4 == null) ? 0 : v4.hashCode());
    result = prime * result + ((v5 == null) ? 0 : v5.hashCode());
    return result;
  }

  /**
   * Compares this tuple object and the other object for equality. 
   * They are equal if the second object is a tuple of five and the five values are equal. 
   * 
   * @param obj the other object 
   * @return {@code true} if the second object is a tuple of five and the five values are equal
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
    Tuple5<A, B, C, D, E> other = (Tuple5<A, B, C, D, E>) obj;
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
    if (v3 == null) {
      if (other.v3 != null)
        return false;
    } else if (!v3.equals(other.v3))
      return false;
    if (v4 == null) {
      if (other.v4 != null)
        return false;
    } else if (!v4.equals(other.v4))
      return false;
    if (v5 == null) {
      if (other.v5 != null)
        return false;
    } else if (!v5.equals(other.v5))
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
    return "(" + v1 + ", " + v2 + ", " + v3 + ", " + v4 + ", " + v5 + ")";
  }

}
