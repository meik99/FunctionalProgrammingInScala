package flist

trait FList[+A] {
  def size: Int
  def isEmpty: Boolean

  def map[B](mapFunction: A => B): FList[B] =
    this match
      case FCons(head, list) => FCons[B](mapFunction(head), list.map(mapFunction))
      case _ => FNil[B]()
}

case class FNil[A]() extends FList[A]:
  override def size: Int = 0
  override def isEmpty: Boolean = true

case class FCons[A](head: A, list: FList[A]) extends FList[A]:
  override def size: Int = 1 + list.size
  override def isEmpty: Boolean = false
