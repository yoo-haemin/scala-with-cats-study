package chap04.catslib

import utest._
import SaferFolding.foldRight

object SaferFoldingSpec extends TestSuite {
  lazy val longList: Stream[Int] = 1 #:: longList
  val loopCount = 100000
  val a = longList.take(loopCount)
  
  val tests = Tests {
    * - {
      assert(foldRight(longList.take(loopCount).toList, 0)(_+_) == loopCount)
    }
    * - {
      List(1, 2).foldLeft(0)(_-_) != foldRight(List(1, 2), 0)(_-_)
    }
  }
}