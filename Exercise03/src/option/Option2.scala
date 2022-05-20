package option

import option.Util.*

import java.util.Scanner

object Option2 {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 1, "y" -> 4, "z" -> 0)


    // 3_2: Pattern matching

    val optX = bds.get("x")
    val optU = bds.get("u")

    patternMatch(optX)
    patternMatch(optU)
    println()

    val customMap = Map("a" -> -1, "b" -> 0, "c" -> -1)

    patternMatch(customMap.get("a"))
    patternMatch(customMap.get("b"))
    patternMatch(customMap.get("c"))
    patternMatch(customMap.get("d"))
  }

  def patternMatch(value: Option[Int]): Unit =
    value match
      case Some(0) => println("the value is zero")
      case Some(a) if a > 0 => println("the value is positive")
      case Some(a) if a < 0 => println("the value is negative")
      case None => println("no value was provided")
      case _ => println("unexpected value was provided")

}
