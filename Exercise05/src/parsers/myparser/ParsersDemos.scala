package parsers.myparser

import parsers.myparser.TokenParsers

object ParsersDemos extends Parsers {

  def char(c: Char): Parser[Char] =
    in =>
      if (in.isEmpty) Failure("Input empty", in)
      else if (c != in.charAt(0)) Failure(s"Char $c expected", in)
      else Success(c, in.substring(1))

  def main(args: Array[String]) = {

    val xParser: Parser[Char] = char('x')
    val yParser: Parser[Char] = char('y')
    val zParser: Parser[Char] = char('z')

    // sequence

    val xyzParser: Parser[((Char, Char), Char)] = xParser ~ yParser ~ zParser

    val xyzR: Result[((Char, Char), Char)] = xyzParser("xyzuv")
    println(xyzR)

    // alternative

    val u_Or_vParser: Parser[Char] = char('u') | char('v')

    val u_Or_vResult = u_Or_vParser("ux")
    println(u_Or_vResult) // Success('u', "x")

    val u_Or_vResult2 = u_Or_vParser("vx")
    println(u_Or_vResult2) // Success('v', "x")

    val u_Or_vxParser: Parser[(Char, Char)] = (char('u') | char('v')) ~ char('x')

    val u_Or_vxResult = u_Or_vxParser("ux")
    println(u_Or_vxResult) // Success(('u', 'x'); "")

    val u_Or_vxResult2 = u_Or_vxParser("vx")
    println(u_Or_vxResult2) // Success(('v', 'x'); "")

    // optional

    val opt_u_Parser: Parser[Option[Char]] = opt(char('u'))

    val opt_u_Result = opt_u_Parser("ux")
    println(opt_u_Result) // Success(Some(u),x)

    val opt_u_Result2 = opt_u_Parser("x")
    println(opt_u_Result2) // Success(None,x)

    val opt_u_xParser: Parser[(Option[Char], Char)] = opt(char('u')) ~ char('x')

    val opt_u_xResult = opt_u_xParser("ux")
    println(opt_u_xResult) // Success((Some(u),x),)

    val opt_u_xResult2 = opt_u_xParser("x")
    println(opt_u_xResult2) // Success((None,x),)

    // repetition

    val rep_xParser: Parser[List[Char]] = rep(char('x'))

    val rep_xResult = rep_xParser("xxxxv")
    println(rep_xResult) // Success(List(x, x, x, x),v)

    val rep_xResult2 = rep_xParser("v")
    println(rep_xResult2) // Success(List(),v)


    // example combination

    val u_Or_vxyParser = (char('u') | char('v')) ~ opt(char('x')) ~ char('y')
    val u_Or_vxyResult = u_Or_vxyParser("uxy")
    println(u_Or_vxyResult)

    val u_Or_vxyyyyParser = (char('u') | char('v')) ~ opt(char('x')) ~ rep(char('y')) ~ char('z')
    val u_Or_vxyyyyResult = u_Or_vxyyyyParser("vxyyyyz")
    println(u_Or_vxyyyyResult)

  }

}
