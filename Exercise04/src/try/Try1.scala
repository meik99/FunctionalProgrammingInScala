package `try`

import java.util.Scanner
import scala.util.Try._
import scala.util._

object Try1 {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 1, "y" -> 4, "z" -> 0)

    // a) x / y
    Try {
      bds("x") / bds("y")
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)

    // b) x / z
    Try {
      bds("x") / bds("z")
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)

    // c) x / u
    Try {
      bds("x") / bds("u")
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)

    // d) x / (y * z)
    Try {
      bds("x") / (bds("y") * bds("z"))
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)

  }
}
