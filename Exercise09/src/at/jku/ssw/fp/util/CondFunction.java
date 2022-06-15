package at.jku.ssw.fp.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Interface for conditional functions.
 * Conditional functions test argument values before returning the result.
 * A conditional function returns an {@link Optional}.
 * If the argument passes the test, the value is mapped to a result and the
 * result is wrapped into the {@link Optional}. But if the test is not successful,
 * {@link Optional#empty()} is returned.
 *
 * @author Herbert Praehofer
 *
 * @param <T> the type of the argument
 * @param <R> the type of the wrapped return value
 */
public interface CondFunction<T, R> extends Function<T, Optional<R>> {

  /**
   * Tests an argument value if applicable.
   *
   * @param t the value to test
   * @return the Boolean result of the test
   */
  boolean test(T t);

  /**
   * Constructs a conditional function from a predicate and a function argument.
   * The resulting conditional function first uses the predicate to test the argument value.
   * If successful, the function is applied and the resulting value is wrapped into an {@link Optional}.
   * Otherwise {@link Optional#empty()} is returned.
   *
   * @param <T> the type of the argument
   * @param <R> the type of the wrapped return value
   * @param pred the predicate
   * @param func the function
   * @return the conditional function
   */
  static <T, R> CondFunction<T, R> of(Predicate<? super T> pred, Function<? super T, ? extends R> func) {
    return new CondFunction<T, R>() {
      @Override
      public Optional<R> apply(T t) {
        if (test(t))
          return Optional.of(func.apply(t));
        else
          return Optional.empty();
      }

      @Override
      public boolean test(T t) {
        return pred.test(t);
      }
    };
  }

  /**
   * Returns a combined conditional function.
   * The returned conditional function first tries to apply this conditional function,
   * and if not successful will try to test the value with the given predicate and
   * apply the given function.
   *
   * @param pred the predicate to test the argument
   * @param func the function to apply on the argument
   * @return the combined conditional function
   */
  default CondFunction<T, R> orElseIf(Predicate<? super T> pred, Function<? super T, ? extends R> func) {
    CondFunction<T, R> _this = this;
    return new CondFunction<T, R>() {
      @Override
      public boolean test(T t) {
        return _this.test(t) || pred.test(t);
      }

      @Override
      public Optional<R> apply(T t) {
        if (_this.test(t))
          return _this.apply(t);
        else if (pred.test(t))
          return Optional.of(func.apply(t));
        else
          return Optional.empty();
      }
    };
  }

  /**
   * Returns a combined conditional function.
   * The returned conditional function first tries to apply this conditional function,
   * and if not successful will alternatively apply the given function.
   *
   * @param func the function to apply on the argument as an alternative
   * @return the combined conditional function
   */
  default CondFunction<T, R> orElse(Function<? super T, ? extends R> func) {
    return orElseIf(x -> true, func);
  }
}
