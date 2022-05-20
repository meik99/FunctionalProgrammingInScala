package tree

import org.scalatest.funsuite.AnyFunSuite
import person.{Person, Professor, Student}

class BinTreeSuite extends AnyFunSuite:
  test("isEmpty") {
    assert(!BinNode(0, EmptyTree(), EmptyTree()).isEmpty)
    assert(EmptyTree().isEmpty)
  }

  test("map") {
    val tree = BinNode(2, BinNode(1, EmptyTree(), EmptyTree()), BinNode(3, EmptyTree(), EmptyTree()))
    val multiple = tree.map(a => a*2)

    assert(multiple.asInstanceOf[BinNode[Int]].elem == 4)
    assert(multiple.asInstanceOf[BinNode[Int]].left.asInstanceOf[BinNode[Int]].elem == 2)
    assert(multiple.asInstanceOf[BinNode[Int]].right.asInstanceOf[BinNode[Int]].elem == 6)
  }

  test("find") {
    val tree = BinNode(2, BinNode(1, EmptyTree(), EmptyTree()), BinNode(3, EmptyTree(), EmptyTree()))
    val two = tree.find(a => a == 2)
    val one = tree.find(a => a == 1)
    val three = tree.find(a => a == 3)
    val nothing = tree.find(a => a == 4)

    assert(two.isDefined)
    assert(two.get == 2)

    assert(one.isDefined)
    assert(one.get == 1)

    assert(three.isDefined)
    assert(three.get == 3)

    assert(nothing.isEmpty)
  }

  test("all") {
    val tree = BinNode(2, BinNode(1, EmptyTree(), EmptyTree()), BinNode(3, EmptyTree(), EmptyTree()))

    assert(tree.all(a => a < 4))
    assert(!tree.all(a => a == 1))
  }

  test("any") {
    val tree = BinNode(2, BinNode(1, EmptyTree(), EmptyTree()), BinNode(3, EmptyTree(), EmptyTree()))

    assert(tree.any(a => a < 4))
    assert(tree.any(a => a == 3))
    assert(!tree.any(a => a == 4))
  }

  test("tree is covariant") {
    val tree: BinTree[Person] =
      BinNode(Professor(name="Teacher", research = "Software Engineering"),
        BinNode(Student(name="Student 1", study="CS"), EmptyTree(), EmptyTree()),
        BinNode(Student(name = "Student 2", study = "WiIT"), EmptyTree(), EmptyTree()))

    assert(tree.find(a => a.name == "Teacher").isDefined)
    assert(tree.find(a => a.name == "Student 1").isDefined)
    assert(tree.find(a => a.name == "Student 2").isDefined)
  }