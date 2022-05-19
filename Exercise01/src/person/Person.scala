package person

trait Person(name: String)

case class Student(name: String, study: String) extends Person(name)

case class Professor(name: String, research: String) extends Person(name)
