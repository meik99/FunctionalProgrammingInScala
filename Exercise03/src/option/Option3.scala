package option

import option.Util.*

import java.util.Scanner

object Option3 {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds = Map("x"-> 1, "y" -> 4, "z" -> 0)

    // 3.3: option
    option({
      bds("x") / bds("y")
    }) match
      case Some(value) => println(value)
      case None => println("exception caught")

    option({
      bds("x") / bds("z")
    }) match
      case Some(value) => println(value)
      case None => println("exception caught")

    option({
      bds("x") / bds("u")
    }) match
      case Some(value) => println(value)
      case None => println("exception caught")
  }

}
