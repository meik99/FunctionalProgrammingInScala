package at.jku.ssw.fp.util;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class with static methods implementing some methods functioning 
 * like conditional statements. 
 * 
 * @author Herbert Praehofer 
 *
 */
public class When {

  /**
   * Methods applying conditional functions on a given value. 
   * The first conditional function which is applicable is applied and 
   * the result is returned. 
   * Throws a {@link NoSuchElementException} if none of the conditional 
   * functions is applicable. 
   * 
   * @param <T> the type of the argument
   * @param <R> the type of the return value
   * @param value the value to apply
   * @param cases the conditional functions to test and apply
   * @return the result value of the conditional application 
   */
  @SafeVarargs
  public static <T, R> R when(T value, CondFunction<? super T, ? extends R>... cases) {
    for (CondFunction<? super T, ? extends R> c : cases) {
      if (c.test(value))
        return c.apply(value).get();
    }
    throw new NoSuchElementException("Select exception: " + value.toString());
  }

  /**
   * Creates a conditional function from a predicate and a function. 
   * 
   * @param <T> the type of the argument
   * @param <R> the type of the return value of the function 
   * @param pred the predicate
   * @param func the function 
   * @return the constructed conditional function 
   */
  public static <T, R> CondFunction<T, R> is(Predicate<? super T> pred, Function<? super T, ? extends R> func) {
    return CondFunction.of(pred, func);
  }

  /**
   * Creates a conditional function from a function and with a 
   * test predicate always returning true
   * 
   * @param <T> the type of the argument
   * @param <R> the type of the return value of the function 
   * @param func the function 
   * @return the constructed conditional function  
   */
  public static <T, R> CondFunction<T, R> otherwise(Function<? super T, ? extends R> func) {
    return CondFunction.of(n -> true, func);
  }

  /**
   * Methods applying typetest functions on a given value. 
   * The first typetest function which is applicable is applied and 
   * the result is returned. 
   * Throws a {@link NoSuchElementException} if none of the conditional 
   * functions is applicable. 
   * 
   * @param <R> the type of the return value
   * @param o the value to apply
   * @param ttFns the typetest functions
   * @return the result value of the application of the first applicable typtest function 
   */
  @SafeVarargs
  public static <R> R when(Object o,  TypeTestFunction<?, ? extends R>...ttFns) {
    for (TypeTestFunction<?, ? extends R> ttFn : ttFns) {
      if (ttFn.test(o)) return ttFn.apply(o).get(); 
  }
  throw new NoSuchElementException("Select type exception: " + o); 
  }
  
  /**
   * Creates a typetest function from a class and a function. 
   * 
   * @param <T> the type of the argument
   * @param <R> the type of the return value of the function 
   * @param clazz the class for the typetest
   * @param func the function 
   * @return the constructed typetest function 
   */
  public static<T, R> TypeTestFunction<T, R> is(
       Class<T> clazz,  Function<? super T, ? extends R> func) {
    return TypeTestFunction.of(clazz, func); 
  }

}
