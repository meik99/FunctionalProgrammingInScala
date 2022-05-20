package option

import option.Util

import java.util.Scanner
import scala.annotation.tailrec
import scala.io.StdIn

object Option1 {

  def main(args: Array[String]): Unit = {

    val scn = Scanner(System.in)
    val bds: Map[String, Int] = Map("x" -> 1, "y" -> 4, "z" -> 0)

    // 3.1.a: isDefined
    testKey(map = bds, key = "x")
    testKey(map = bds, key = "y")
    testKey(map = bds, key = "z")
    testKey(map = bds, key = "a")
    println()

    // 3.1.b: getOrElse
    testGetOrElse(map = bds, key = "x")
    testGetOrElse(map = bds, key = "y")
    testGetOrElse(map = bds, key = "z")
    testGetOrElse(map = bds, key = "a")
    println()


    // 3.1.c: orElse
    println(bds.get("x").orElse(readUserInput()).get)
    println(bds.get("a").orElse(readUserInput()).get)

  }

  def testKey(map: Map[String, Int], key: String): Unit =
    if map.get(key).isDefined then println(map.get(key).get) else println(s"key '$key' does not exist")

  def testGetOrElse(map: Map[String, Int], key: String): Unit =
    println(map.getOrElse(key, s"key '$key' does not exist'"))

  @tailrec
  def readUserInput(): Option[Int] =
    try {
      print("key does not exist, enter value: ")
      Some(StdIn.readInt())
    } catch {
      case e: Exception =>
        println(s"invalid input: ${e.getMessage}")
        readUserInput()
    }
}
