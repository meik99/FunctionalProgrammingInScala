package `try`

import java.util.Scanner
import scala.io.StdIn
import scala.util.*
import scala.util.Try.*

object Try3_recover {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 7, "y" -> 0, "z" -> 0)

    // a) x / y
    println(for(
      x <- safeGet(bds, "x");
      y <- safeGet(bds, "y")
    ) yield safeDivide(x, y))

    // b) x / z
    println(for(
      x <- safeGet(bds, "x");
      z <- safeGet(bds, "z")
    ) yield safeDivide(x, z))

    // c) x / u
    println(for(
      x <- safeGet(bds, "x");
      u <- safeGet(bds, "u")
    ) yield safeDivide(x, u))

    // d) x / (y * z)
    println(for(
      x <- safeGet(bds, "x");
      y <- safeGet(bds, "y");
      z <- safeGet(bds, "z")
    ) yield safeDivide(x, y * z))

  }

  def safeDivide(a: Int, b: Int): Try[Int] =
    Try {
      a / b
    } recover {
      case _: ArithmeticException =>
        println("divider is zero")
        print("input replacement value: ")
        a / StdIn.readInt()
    }

  def safeGet(map: Map[String, Int], key: String): Try[Int] = {
    Try { map(key) } recoverWith {
      case _: NoSuchElementException =>
        println(s"key '$key' does not exist")
        print("enter replacement value: ")
        Try { StdIn.readInt() }
      case _ => Try { 0 }
    }
  }

}
