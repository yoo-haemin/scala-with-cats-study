package chap04

import utest._
import StackSafe._

object StackSafeSpec extends TestSuite {
  val tests = Tests{
    "It should format int box" - {
     lazy val longList: Stream[Int] = 0 #:: longList

      assert(foldRight(longList.take(10000).toList, 0)(_ + _) == 0)

//      lazy val longList2: Stream[Int] = 1 #:: longList2
//      println("Hello")
//      println(foldRight(longList2.take(1000000).toList, 0)(_ + _))
    }
  }
}

