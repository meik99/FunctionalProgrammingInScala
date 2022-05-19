package person

trait Person(name: String)

case class Student(name: String, study: String) extends Person(name)

case class Profesor(name: String, research: String) extends Person(name)
