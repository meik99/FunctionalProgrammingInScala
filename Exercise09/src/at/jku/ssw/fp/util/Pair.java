package at.jku.ssw.fp.util;

import java.io.Serializable;

/**
 * Pair of two generic values.
 *
 * @author Herbert Praehofer
 *
 * @param <A> the type of the first value
 * @param <B> the type of the second value
 */
@SuppressWarnings("serial")
public class Pair<A, B> implements Serializable {

  /**
   * Creates a pair of two values.
   *
   * @param <A> the type of the first value
   * @param <B> the type of the second value
   * @param fst the first value
   * @param snd the second value
   * @return the pair of first and second value
   */
  public static <A, B> Pair<A, B> of(A fst, B snd) {
    return new Pair<A, B>(fst, snd);
  }

  /** The first value. */
  public final A fst;

  /** The second value. */
  public final B snd;

  /**
   * Private constructor.
   *
   * @param fst the first value
   * @param snd the second value
   */
  private Pair(A fst, B snd) {
    this.fst = fst;
    this.snd = snd;
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
    result = prime * result + ((fst == null) ? 0 : fst.hashCode());
    result = prime * result + ((snd == null) ? 0 : snd.hashCode());
    return result;
  }

  /**
   * Compares this pair object and the other object for equality.
   * They are equal if the second object is a pair and the two values are equal.
   *
   * @param obj the other object
   * @return {@code true} if the second object is a pair and the two values are equal
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
    Pair<A, B> other = (Pair<A, B>) obj;
    if (fst == null) {
      if (other.fst != null)
        return false;
    } else if (!fst.equals(other.fst))
      return false;
    if (snd == null) {
      if (other.snd != null)
        return false;
    } else if (!snd.equals(other.snd))
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
    return "(" + fst + ", " + snd + ")";
  }

}
