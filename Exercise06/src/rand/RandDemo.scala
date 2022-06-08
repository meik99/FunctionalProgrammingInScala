package rand

object RandDemo {

  def main(args: Array[String]): Unit = {

    import Rand.*
    val rng = BasicRNG(7)
    val (i1, rng1) = randInt(rng)
    println(i1)

  }

}
