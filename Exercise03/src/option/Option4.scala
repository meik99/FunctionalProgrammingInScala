package option

import option.Util.*

import java.util.Scanner

object Option4 {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x" -> 1, "y" -> 4, "z" -> 0)

    // a) x / y
    println(option(bds.get("x").flatMap(x =>
      bds.get("y").flatMap(y => Some(x / y)))
    ))

    // b) x / z
    println(option(bds.get("x").flatMap(x =>
      bds.get("z").flatMap(y => Some(x / y)))
    ))

    // c) x / u
    println(option(bds.get("x").flatMap(x =>
      bds.get("u").flatMap(y => Some(x / y)))
    ))

    // d) x / (y * z)
    println(option(bds.get("x").flatMap(x =>
      bds.get("y").flatMap(y =>
        bds.get("z").flatMap(z => Some(x / (y * z)))))
    ))
  }

}
