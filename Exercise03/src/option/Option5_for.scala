package option

import java.util.Scanner
import option.Util.*

object Option5_for {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 9, "y" -> 4, "z" -> 0)

    // a) x / y
    println(for(
      x <- bds.get("x");
      y <- bds.get("y")
    ) yield option(x / y))

    // b) x / z
    println(for(
      x <- bds.get("x");
      z <- bds.get("z")
    ) yield option(x / z))

    // c) x / u
    println(for(
      x <- bds.get("x");
      u <- bds.get("u")
    ) yield option(x / u))


    // d) x / (y * z)
    println(for(
      x <- bds.get("x");
      y <- bds.get("y");
      z <- bds.get("z")
    ) yield option(x / (y * z)))
  }
}
