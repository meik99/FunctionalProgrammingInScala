package expr

sealed trait Expr
case class Lit(value: Double) extends Expr
case class Var(name: String) extends Expr
abstract class BinExpr(left: Expr, right: Expr) extends Expr
case class Add(left: Expr, right: Expr) extends BinExpr(left, right)
case class Mult(left: Expr, right: Expr) extends BinExpr(left, right)
abstract class UnyExpr(sub: Expr) extends Expr
case class Minus(sub: Expr) extends UnyExpr(sub)
case class Recip(sub: Expr) extends UnyExpr(sub)

object Expr {

  def eval(expr: Expr, bds: Map[String, Double]): Double =
    expr match {
      case Lit(value) =>
        {
          value
        }
      case Var(name) =>
        {
          bds(name)
        }
      case Minus(sub) =>
        {
          val sr = eval(sub, bds)
          return -sr
        }
      case Recip(sub) =>
        {
          val sr = eval(sub, bds)
          return 1 / sr
        }
      case Add(l, r) =>
        {
          val lr = eval(l, bds)
          val rr = eval(r, bds)
          return lr + rr
        }
      case Mult(l, r) =>
        {
          val lr = eval(l, bds)
          val rr = eval(r, bds)
          return lr * rr
        }
    }

}
