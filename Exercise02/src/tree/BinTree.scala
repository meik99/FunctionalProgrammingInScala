package tree

trait BinTree[+A] {
  def isEmpty: Boolean

  def map[B](mapFunction: A => B): BinTree[B] =
    this match
      case BinNode(elem, left, right) => BinNode(mapFunction(elem), left.map(mapFunction), right.map(mapFunction))
      case _ => EmptyTree()

  def find(predicate: A => Boolean): Option[A] =
    this match
      case BinNode(elem, left, right) =>
        if predicate(elem) then Some(elem) else left.find(predicate) match
          case Some(result) => Some(result)
          case None => right.find(predicate)
          case null => None
      case _ => None
      
  def all(predicate: A => Boolean): Boolean =
    this match
      case BinNode(elem, left, right) =>
        if !predicate(elem) then false else left.all(predicate) && right.all(predicate)
      case _ => true
      
  def any(predicate: A => Boolean): Boolean =
    this match
      case BinNode(elem, left, right) =>
        if predicate(elem) then true else left.any(predicate) || right.any(predicate)
      case _ => false
}

case class EmptyTree[+A]() extends BinTree[A]:
  override def isEmpty: Boolean = true

case class BinNode[+A](elem: A, left: BinTree[A], right: BinTree[A]) extends BinTree[A]:
  override def isEmpty: Boolean = false

