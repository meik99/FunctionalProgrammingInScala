package parsers.myparser

import parsers.myparser.Parsers

trait TokenParsers extends Parsers {

  private def skipWhiteSpaces(str: String): String = {
    str.substring(str.indexWhere(!Character.isWhitespace(_)))
  }


  def char(c: Char): Parser[Char] =
    in =>
      if (in.isEmpty) Failure("Input empty", in)
      else if (c != in.charAt(0)) Failure(s"Char $c expected", in)
      else Success(c, in.substring(1))

  val letter: Parser[Char] = char('a') | char('b') | char('c') | char('d') | char('e') | char('f') | char('g') |
    char('h') | char('i') | char('j') | char('k') | char('l') | char('m') | char('n') | char('o') | char('p') |
    char('q') | char('r') | char('s') | char('t') | char('u') | char('v') | char('w') | char('x') | char('y') | char('z')

  val alphabetic: Parser[List[Char]] = rep(letter)

  val digit: Parser[Char] = char('0') | char('1') | char('2') | char('3') | char('4') | char('5') | char('6') |
    char('7') | char('8') | char('9')

  val digits: Parser[List[Char]] = rep(digit)

  val token: Parser[String] =
    inp => {
      val tokenParser = rep(letter | digit).map(characters => String.valueOf(characters.toArray[Char]))
      tokenParser(skipWhiteSpaces(inp))
    }

  def word(matchWord: String) : Parser[String] = token.filter(anyToken => anyToken == matchWord)

  val int: Parser[Int] = token.map(t => t.toInt)

  val bool : Parser[Boolean] =
    word("true").map(_ => true) | word("false").map(_ => false)

  // implicits conversions


  private def digitsToInt(digits: List[Char]): Int = {
    var value = 0
    for (digit <- digits) {
      value *= 10
      value += digit - '0'
    }
    value
  }

}
