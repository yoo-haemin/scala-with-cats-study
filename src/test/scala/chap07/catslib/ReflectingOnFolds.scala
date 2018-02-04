package chap07.catslib

import utest._

object ReflectingOnFolds extends TestSuite {
  val testList = List(1, 2, 3)

  val tests = Tests {
    'foldLeft - {
      assert(testList.foldLeft(List.empty[Int]) { (acc, v) => v :: acc } == testList.reverse)
    }

    'foldRight - {
      assert(testList.foldRight(List.empty[Int]) { (v, acc) => v :: acc } == testList)
    }
  }
}
