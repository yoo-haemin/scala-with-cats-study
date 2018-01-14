package chap04.catslib

import utest._
import ShowYourWorking.factorial

object WriterSpec extends TestSuite {
  val tests = Tests {
    // * - {
    //   assert(factorial(1).run == (Vector("fact 1 1") -> 1))
    //   println(factorial(3).run)
    //   assert(factorial(2).run == (Vector("fact 1 1", "fact 2 2") -> 2))
    // }
  }
}
