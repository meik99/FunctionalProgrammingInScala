package person

// Exposing name for easier unit testing
trait Person(val name: String)

case class Student(override val name: String, study: String) extends Person(name)

case class Professor(override val name: String, research: String) extends Person(name)
