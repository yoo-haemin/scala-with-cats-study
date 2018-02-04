package chap02

import utest._
import MonoidLaw._
import scala.collection.immutable.Set

//Exercise 2.4
object SetSpec extends TestSuite {
  val tests = Tests {
    val set1 = Set(1, 2, 3)
    val set2 = Set.empty[Int]
    val set3 = Set(3, 4, 5)

    "union" - {
      import SetMonoid.union

      assert(
        associativeLaw(set1, set2, set3),
        identityLaw(set1)
      )
    }
  }
}
