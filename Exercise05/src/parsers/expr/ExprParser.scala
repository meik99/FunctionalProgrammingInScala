package parsers.expr

import expr.{Expr, Lit, Mult, Var}

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.{CharSequenceReader, PagedSeqReader}


object ExprParser extends JavaTokenParsers {

  // I have literally no idea where to even begin
  def expr : Parser[Expr] = ???
}

object ExprParserApp {

  import ExprParser.*

  def main(args: Array[String]): Unit = {
    val parseResult : ParseResult[Expr] = parseAll(expr, "((1 + x) * ((- 1) * (/ y)))")
    println(parseResult)
  }

}