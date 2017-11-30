package chap02

import utest._
import MonoidLaw._

//Exericse 2.3
object TruthSpec extends TestSuite {
  val bool1 = true
  val bool2 = false
  val bool3 = false

  val tests = Tests {
    'and - {
      import Truth.and

      assert(
        associativeLaw(bool1, bool2, bool3),
        identityLaw(bool1)
      )
    }

    'or - {
      import Truth.or

      assert(
        associativeLaw(bool1, bool2, bool3),
        identityLaw(bool1)
      )
    }
  }
}
