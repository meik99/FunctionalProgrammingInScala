package `try`

import java.util.Scanner
import scala.util.Try._
import scala.util._

object Try2_for {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 1, "y" -> 4, "z" -> 0)

    // a) x / y
    Try {
      for (
        x <- bds.get("x");
        y <- bds.get("y")
      ) yield x / y
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)

    // b) x / z

    Try {
      for (
        x <- bds.get("x");
        z <- bds.get("z")
      ) yield x / z
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)



    // c) x / u
    Try {
      for (
        x <- bds.get("x");
        u <- bds.get("u")
      ) yield x / u
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)


    // d) x / (y * z)
    Try {
      for (
        x <- bds.get("x");
        y <- bds.get("y");
        z <- bds.get("z")
      ) yield x / (y * z)
    } match
      case Failure(exception) => println(exception.getMessage)
      case Success(value) => println(value)
  }

}
