package parsers.myparser

import parsers.myparser.TokenParsers

object TokenParsersDemos extends TokenParsers {

  def main(args: Array[String]) = {
    val Success(number, _) = int("123")
    println(number)

    val s1@Success(_, rest) = token("hallo world here i am")
    println(s1)
    val s2@Success(_, _) = token(rest)
    println(s2)

    val s3@Success(_, r3) = int("123 456 x")
    println(s3)
    val s4@Success(_, r4) = int(r3)
    println(s4)
    val s5 = int(r4)
    println(s5)

    val s6 = bool("true false")
    println(s6)
    val s7 = bool(s6.rest)
    println(s7)
  }

}
