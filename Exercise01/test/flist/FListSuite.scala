package flist

import org.scalatest.funsuite.AnyFunSuite
import person.{Person, Professor, Student}

class FListSuite extends AnyFunSuite:
  test("list returns correct size") {
    val list = FCons(1, FCons(2, FCons(3, FNil())))

    assert(list.size == 3)
  }

  test("list returns correct isEmpty") {
    val nonEmptyList = FCons(1, FNil())

    assert(!nonEmptyList.isEmpty)

    val emptyList = FNil()

    assert(emptyList.isEmpty)
  }

  test("list is co-variant") {
    val list: FList[Person] = FCons(
      Student(name = "Student Name", study = "CS"),
      FCons(
        Professor(name = "Professor Name", research = "Software Engineering"),
        FNil()
      ))

    assert(list.find(p => p.name == "Student Name").isDefined)
    assert(list.find(p => p.name == "Professor Name").isDefined)
  }

  test("map") {
    val list = FCons(1, FCons(2, FCons(3, FNil())))
    val mapped = list.map(a => a*2)

    var tmp = mapped
    var i = 1
    while tmp.isInstanceOf[FCons[Int]] do {
      assert(tmp.asInstanceOf[FCons[Int]].head == i*2)
      i += 1
      tmp = tmp.asInstanceOf[FCons[Int]].tail
    }
  }

  test("filter") {
    val list = FCons(true, FCons(false, FCons(true, FCons(true, FCons(false, FNil())))))
    // Filter such that only true values remain
    val filtered = list.filter(a => a)

    assert(filtered.size == 3)

    var tmp = filtered
    while tmp.isInstanceOf[FCons[Boolean]] do {
      assert(tmp.asInstanceOf[FCons[Boolean]].head)
      tmp = tmp.asInstanceOf[FCons[Boolean]].tail
    }
  }

  test("find") {
    val list = FCons(1, FCons(2, FCons(3, FNil())))
    val one = list.find(a => a == 1)
    val two = list.find(a => a == 2)
    val three = list.find(a => a == 3)
    val nothing = list.find(a => a == 4)

    assert(one.isDefined)
    assert(one.get == 1)

    assert(two.isDefined)
    assert(two.get == 2)

    assert(three.isDefined)
    assert(three.get == 3)

    assert(nothing.isEmpty)
  }

  test("all") {
    val list = FCons(true, FCons(false, FCons(true, FCons(true, FCons(false, FNil())))))
    val allTrue = FCons(true, FCons(true, FCons(true, FCons(true, FCons(true, FNil())))))

    assert(!list.all(a => a))
    assert(allTrue.all(a => a))
  }

  test("any") {
    val list = FCons(true, FCons(false, FCons(true, FCons(true, FCons(false, FNil())))))
    val allTrue = FCons(true, FCons(true, FCons(true, FCons(true, FCons(true, FNil())))))

    assert(list.any(a => !a))
    assert(!allTrue.any(a => !a))
  }

  test("drop") {
    val list = FCons(1, FCons(2, FCons(3, FCons(4, FCons(5, FNil())))))
    val emptyList = FNil()
    val lastThree = list.drop(2)

    assert(list.drop(10).isEmpty)
    assert(list.drop(-1).size == 5)
    assert(list.drop(0).size == 5)

    assert(emptyList.drop(1).isEmpty)

    assert(lastThree.size == 3)

    var tmp = lastThree
    var i = 3
    while tmp.isInstanceOf[FCons[Int]] do {
      assert(tmp.asInstanceOf[FCons[Int]].head == i)
      tmp = tmp.asInstanceOf[FCons[Int]].tail
      i += 1
    }
  }

  test("take") {
    val list = FCons(1, FCons(2, FCons(3, FCons(4, FCons(5, FNil())))))
    val emptyList = FNil()
    val firstThree = list.take(3)

    assert(list.take(10).isEmpty)
    assert(list.take(-1).isEmpty)
    assert(list.take(0).isEmpty)

    assert(emptyList.take(1).isEmpty)

    assert(firstThree.size == 3)

    var tmp = firstThree
    var i = 1
    while tmp.isInstanceOf[FCons[Int]] do {
      assert(tmp.asInstanceOf[FCons[Int]].head == i)
      tmp = tmp.asInstanceOf[FCons[Int]].tail
      i += 1
    }
  }

  test("dropWhile") {
    val list = FCons(1, FCons(2, FCons(3, FCons(4, FCons(5, FNil())))))
    val dropped = list.dropWhile(a => a < 3)

    assert(dropped.size == 3)
    assert(dropped.isInstanceOf[FCons[Int]])
    assert(dropped.asInstanceOf[FCons[Int]].head == 3)

    assert(FNil[Int]().dropWhile(a => a < 3).isEmpty)
  }

  test("takeWhile") {
    val list = FCons(1, FCons(2, FCons(3, FCons(4, FCons(5, FNil())))))
    val taken = list.takeWhile(a => a < 3)

    assert(taken.size == 2)
    assert(taken.isInstanceOf[FCons[Int]])
    assert(taken.asInstanceOf[FCons[Int]].head == 1)

    assert(FNil[Int]().takeWhile(a => a < 3).isEmpty)
  }