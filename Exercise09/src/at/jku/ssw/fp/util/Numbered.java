package at.jku.ssw.fp.util;

import java.util.Comparator;

/**
 * Class for pairs of a number and a value.
 *
 * @author Herbert Praehofer
 *
 * @param <A> the type of the values
 */
public class Numbered<A extends Comparable<? super A>> implements Comparable<Numbered<A>> {

  /**
   * Comparator for numbered values by first comparing the number and then the value.
   */
  private Comparator<Numbered<A>> comparator = Comparator.<Numbered<A>>comparingInt(numA -> numA.n).thenComparing(numA -> numA.value);

  /**
   * Creates a numbered value by a number and a value.
   *
   * @param <A> the type of the value
   * @param n the number
   * @param value the value
   * @return the numbered value
   */
  public static<A extends Comparable<? super A>> Numbered<A> of(int n, A value){
    return new Numbered<A>(n, value);
  }

  /** The number */
  public final int n;

  /** The value */
  public final A value;

  /**
   * Private constructor initializing number and value.
   *
   * @param n the number
   * @param value the value
   */
  private Numbered(int n, A value) {
    this.n = n;
    this.value = value;
  }

  @Override
  public int compareTo(Numbered<A> other) {
    return comparator.compare(this, other);
  }

  @Override
  public String toString() {
    return "Numbered [n=" + n + ", value=" + value + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + n;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("rawtypes")
    Numbered other = (Numbered) obj;
    if (n != other.n)
      return false;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }

}
