package at.jku.ssw.fp.util;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

/**
 * Class with some miscellaneous utility methods.
 *
 * @author Herbert Praehofer
 *
 */
public class Util {

  /**
   * Returns an optional value from getting a value from a map based on a key.
   * If no value exists in the map for the key, an empty optional is returned.
   *
   * @param <A> the type of the key
   * @param <B> the type of the value
   * @param map the map
   * @param key the key
   * @return an optional value with an empty optional if no value exists for the key
   */
  public static <A, B> Optional<B> lookup(Map<A, B> map, A key) {
    return Optional.ofNullable(map.get(key));
  }


  /**
   * Runs the given callable and returns the value wrapped in an optional.
   * If the callable throws and exception, an empty optional is returned.
   * @param <R> the result value
   * @param callable the callable to execute
   * @return the return value wrapped in an optional,
   *         with an empty optional in case of an exception
   */
  public static <R> Optional<R> optional(Callable<R> callable) {
    try {
      return Optional.of(callable.call());
    } catch (Exception e) {
      return Optional.empty();
    }
  }

 /**
   * Executes a runnable with the given lock held.
   * @param lock the lock
   * @param block the runnable to execute with the lock held
   */
  public static void withLock(Lock lock, Runnable block) {
    lock.lock();
    try {
      block.run();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Runs the given runnable only if the condition is true.
   *
   * @param condition the condition
   * @param code the runnable to execute based on the condition
   */
  public static void runIf(boolean condition, Runnable code) {
    if (condition)
      code.run();
  }

  /**
   * Delays execution for the given milliseconds.
   *
   * @param ms the delay in milliseconds
   */
  public static void delay(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }

  /**
   * Prints out a log message on standard output.
   *
   * @param msg the log message
   */
  public static void log(String msg) {
    long t = System.currentTimeMillis();
    System.out.format("%d.%03d %s: %s %n", t / 1_000, t % 1_000,
        Thread.currentThread().getName(), msg);
  }

}
