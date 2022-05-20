package flist

trait FList[+A] {
  def size: Int
  def isEmpty: Boolean

  def map[B](mapFunction: A => B): FList[B] =
    this match
      case FCons(head, tail) => FCons[B](mapFunction(head), tail.map(mapFunction))
      case _ => FNil[B]()

  def filter(predicate: A => Boolean): FList[A] =
    this match
      case FCons(head, tail) =>
        if predicate(head) then FCons(head, tail.filter(predicate)) else tail.filter(predicate)
      case _ => FNil()

  def find(predicate: A => Boolean): Option[A] =
    this match
      case FCons(head, tail) =>
        if predicate(head) then Option(head) else tail.find(predicate)
      case _ => Option.empty

  def all(predicate: A => Boolean): Boolean =
    this match
      case FCons(head, tail) =>
        if !predicate(head) then false else tail.all(predicate)
      case FNil() => true
      case _ => false

  def any(predicate: A => Boolean): Boolean =
    this match
      case FCons(head, tail) => if predicate(head) then true else tail.any(predicate)
      case _ => false

  def drop(n: Int): FList[A] =
    if n >= this.size then FNil()
    else if n <= 0 then this
    else this match
      case FCons(_, tail) => tail.drop(n-1)
      case _ => this

  def take(n: Int): FList[A] =
    if n >= this.size then FNil()
    else if n <= 0 then FNil()
    else this match
      case FCons(head, tail) => FCons(head, tail.take(n-1))
      case _ => FNil()

  def dropWhile(predicate: A => Boolean): FList[A] =
    this match
      case FCons(head, tail) =>
        if predicate(head) then tail.dropWhile(predicate) else this
      case _ => this
      
  def takeWhile(predicate: A => Boolean): FList[A] =
    this match
      case FCons(head, tail) => 
        if predicate(head) then FCons(head, tail.takeWhile(predicate)) else FNil()
      case _ => FNil()
}

case class FNil[+A]() extends FList[A]:
  override def size: Int = 0
  override def isEmpty: Boolean = true

case class FCons[+A](head: A, tail: FList[A]) extends FList[A]:
  override def size: Int = 1 + tail.size
  override def isEmpty: Boolean = false
