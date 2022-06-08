package rand

import scala.::

trait Rand[+A] extends (RNG => (A, RNG)) :
  def apply(rng: RNG) : (A, RNG)  // inherited

  def map[B](f: A => B): Rand[B] =
    inputSeed => {
      val (randomNumber, nextSeed) = this(inputSeed)
      (f(randomNumber), nextSeed)
    }

  def flatMap[B](f: A => Rand[B]): Rand[B] =
    inputSeed => {
      val (randomNumber, nextSeed) = this(inputSeed)
      f(randomNumber)(nextSeed)
    }

  def filter(predicate: A => Boolean): Rand[A] =
    inputSeed => {
      val (randomNumber, nextSeed) = this(inputSeed)
      predicate(randomNumber)
      this(nextSeed)
    }

  def +[B](that: Rand[B]): Rand[(A, B)] =
    inputSeed => {
      val (a, aSeed) = this(inputSeed)
      val (b, bSeed) = that(aSeed)
      ((a, b), bSeed)
    }

  def list(n: Int): Rand[List[A]] =
    inputSeed =>
      if n == 0 then (List(), inputSeed)
      else {
        val (a, nextSeed) = this(inputSeed)
        val (list2, listSeed) = list(n - 1)(nextSeed)
        (a :: list2, listSeed)
      }

object Rand :
  val randInt: Rand[Int] = rng => rng.nextInt
  val double: Rand[Double] = inputSeed => inputSeed.double

  def intFromTo(from: Int, to: Int): Rand[Int] = inputSeed => {
    val (a, nextSeed) = inputSeed.nextIntTo(to - from)
    (a + from, nextSeed)
  }

  def intTo(to: Int): Rand[Int] = inputSeed => inputSeed.nextIntTo(to)

  def oneOf[A](elems: List[A]): Rand[A] = inputSeed => {
    val (listIndex, nextSeed) = inputSeed.nextIntTo(elems.size)
    (elems(listIndex), nextSeed)
  }

  val letter : Rand[Char] = inputSeed => {
    val (characterNum, nextSeed) = inputSeed.nextIntTo(26)
    ((97+characterNum).toChar, nextSeed)
  }

  def word(max: Int): Rand[String] = inputSeed => {
    val (length, nextSeed) = inputSeed.nextIntTo(max-2)
    var result = ""
    var seed = nextSeed

    for (n <- 2 to length+2) {
      val letterResult = letter(seed)
      result = result.appended(letterResult._1)
      seed = letterResult._2
    }

    (result, seed)
  }


object RandDemoApp :

  import Rand.*

  def main(args: Array[String]): Unit = {

    val rng = BasicRNG(7)

    val (i1, rng1) = randInt(rng)
    println(i1)

    for (n <- 0 to 1000) {
      val (c, rng2): (Char, RNG) = letter(BasicRNG(n))
      println(c)
    }

  }

