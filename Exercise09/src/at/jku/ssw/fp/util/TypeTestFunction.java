package at.jku.ssw.fp.util;

import java.util.Optional;
import java.util.function.Function;

/**
 * Interface for conditional functions which do a type test of the
 * argument value.
 * If the argument passes the type test, the value is mapped to a result and the
 * result is wrapped into the {@link Optional}. But if the type test is not successful,
 * {@link Optional#empty()} is returned.
 *
 * @author Herbert Praehofer
 *
 * @param <T> the type of the argument
 * @param <R> the type of the wrapped return value
 */
public class TypeTestFunction<T, R> implements CondFunction<Object, R> {

  /**
   * Creates a typetest function from a class and a function.
   *
   * @param <T> the type of the argument
   * @param <R> the type of the wrapped return value
   * @param clazz the class for the typetest
   * @param func the function
   * @return the typetest function
   */
  public static <T, R> TypeTestFunction<T, R> of(Class<T> clazz, Function<? super T, ? extends R> func) {
    return new TypeTestFunction<T, R>(clazz, func);
  }

  /** The class for the typetest. */
  private final Class<T> clazz;

  /** The function to apply if the test is successful. */
  private final Function<? super T, ? extends R> func;

  /**
   * Private constructor.
   * @param clazz the class for the typetest
   * @param func the function to apply if the test is successful
   */
  private TypeTestFunction(Class<T> clazz, Function<? super T, ? extends R> func) {
    this.clazz = clazz;
    this.func = func;
  }

  /**
   * Tests the object if it is of the specific class.
   *
   * @param o the object to test
   * @return {@code true} if the object is of the specific class.
   */
  @Override
  public boolean test(Object o) {
    return clazz.isAssignableFrom(o.getClass());
  }

  /**
   * Tests the object and then applies the function on the object.
   * The result is wrapped into an {@link Optional}.
   *
   * @param o the object to test and map
   * @return the result of the function application in an {@link Optional},
   *         if the test was successful; {@link Optional#empty()} otherwise
   */
  @SuppressWarnings("unchecked")
  @Override
  public Optional<R> apply(Object o) {
    if (test(o))
      return Optional.of(func.apply((T) o));
    else
      return Optional.empty();
  }

}
