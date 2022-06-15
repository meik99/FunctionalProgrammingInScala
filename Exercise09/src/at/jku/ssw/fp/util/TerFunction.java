package at.jku.ssw.fp.util;

/**
 * Functional interface for ternary functions.
 *
 * @author Herbert Praehofer
 *
 * @param <T1> the type of the first argument
 * @param <T2> the type of the second argument
 * @param <T3> the type of the third argument
 * @param <R> the type of the return value
 */
public interface TerFunction<T1, T2, T3, R> {

  /**
   * Abstract method of the ternary function.
   * @param t1 the first argument
   * @param t2 the second argument
   * @param t3 the third argument
   * @return the result value
   */
  public R apply(T1 t1, T2 t2, T3 t3);

}
