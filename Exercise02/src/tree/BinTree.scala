package tree

trait BinTree[+A] {
  def isEmpty: Boolean
}

case class EmptyTree[+A]() extends BinTree[A]:
  override def isEmpty: Boolean = true

case class BinNode[+A](elem: A, left: BinTree[A], right: BinTree[A]) extends BinTree[A]:
  override def isEmpty: Boolean = elem match
    case BinNode(_, _, _) => false
    case _ => true

