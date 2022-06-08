package rand

object RNGDemo {

  def main(args: Array[String]): Unit = {

    val rng1: RNG = BasicRNG(7)
    val (d, rng2) = rng1.double
    println(d)

    val ((i1, i2), rng3) = rng2.pairOfInts
    println(i1)
    println(i2)

    val (tenInts, rng4) = rng3.listOfInts(6, 10)
    println(tenInts)

  }

}
