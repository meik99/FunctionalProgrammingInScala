package rand

trait RNG {
  def nextInt : (Int, RNG)

  def posInt : (Int, RNG) = {
    val (i, rng) = this.nextInt
    (if (i < 0) then -(i + 1) else i, rng)
  }

  def nextIntTo(to: Int) : (Int, RNG) = {
    val (i, rng) = this.posInt
    (i % to, rng)
  }

  def double : (Double, RNG) = {
    val (i, rng) = this.posInt
    (i.toDouble / Integer.MAX_VALUE, rng)
  }

  def pairOfInts : ((Int, Int), RNG) = {
    val (i1, rng1) = this.nextInt
    val (i2, rng2) = rng1.nextInt
    ((i1, i2), rng2)
  }

  def listOfInts(to: Int, count: Int) : (List[Int], RNG) = {
    if (count <= 0) then (List(), this)
    else {
      val (x, rng1) = this.nextIntTo(to)
      val (xs, rng2) = rng1.listOfInts(to, count - 1)
      (x :: xs, rng2)
    }
  }

}

case class BasicRNG(seed: Long) extends RNG {

  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = BasicRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

}

