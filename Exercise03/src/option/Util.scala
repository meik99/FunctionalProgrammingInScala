package option

object Util {

  // method option

  def option[A](body: => A): Option[A] =
    try {
      Some(body)
    } catch {
      case _: Exception => None
    }
}
