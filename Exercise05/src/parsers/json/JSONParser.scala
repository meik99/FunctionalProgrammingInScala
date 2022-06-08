package parsers.json

import expr.Expr

import java.io.FileReader
import scala.util.parsing.combinator.JavaTokenParsers

object JSONParser extends JavaTokenParsers {


}

object JSONParserApp {

  import JSONParser.*

  def main(args: Array[String]): Unit = {
    val reader = new FileReader("address-book.json")
    //val parseResult : ParseResult[Any] = parseAll(value, reader)
    //println(parseResult)
  }

}