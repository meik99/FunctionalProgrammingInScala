package parsers.myparser

trait Parsers {

  trait Result[+T]:
    val rest: String

  case class Success[+T](result: T, rest: String) extends Result[T]

  case class Failure(message: String, rest: String) extends Result[Nothing]

  trait Parser[+T] extends (String => Result[T]) {
    thisParser: Parser[T] =>

    def apply(in: String): Result[T] // inherited

    def ~[U](sndParser: => Parser[U]): Parser[(T, U)] =
      inp =>
        thisParser(inp) match {
          case Success(left, leftRest) => sndParser(leftRest) match {
            case Success(right, rightRest) => Success((left, right), rightRest)
            case f2@Failure(msg, inp) => f2
          }
          case f1@Failure(msg, inp) => f1
        }

    def |[U >: T](otherParser: => Parser[U]): Parser[U] =
      inp => {
        thisParser(inp) match {
          case s@Success(_, _) => s
          case Failure(_, _) => otherParser(inp)
        }
      }

    def map[R](f: T => R): Parser[R] =
      inp => {
        thisParser(inp) match {
          case Success(result, rest) =>
            try {
              Success(f(result), rest)
            } catch {
              case e: Exception => Failure(e.toString, inp)
            }
          case failed@Failure(_, _) => failed
        }
      }

    def filter(pred: T => Boolean): Parser[T] =
      inp =>
        thisParser(inp) match {
          case s@Success(result, rest) =>
            if (pred(result)) then s
            else Failure("Failed filter predicate", inp)
          case failed@Failure(_, _) => failed
        }

  }

  def opt[T](optParser: Parser[T]): Parser[Option[T]] =
    inp =>
      optParser(inp) match {
        case Success(r, rest) => Success(Some(r), rest)
        case Failure(_, _) => Success(None, inp)
      }


  def rep[T](parser: Parser[T]): Parser[List[T]] =
    new Parser[List[T]] {
      def apply(in: String): Result[List[T]] = {
        parser(in) match {
          case Success(r, rest) =>
            this (rest) match {
              case Success(rs, rest2) => Success(r :: rs, rest2)
              case Failure(_, rest2) => Success(Nil, rest2)
            }
          case Failure(_, rest) => Success(Nil, rest)
        }
      }
    }

}
