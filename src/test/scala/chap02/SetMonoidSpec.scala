package chap02

import utest._
import Monoid2._
import SetMonoid._

object SetMonoidSpec extends TestSuite{

  val tests = Tests{
    "Int Set Monoid should hold monoid law" - {
      val x = Set(1, 2, 3)
      val y = Set(4, 5, 6)
      val z = Set(7, 8, 9)

      assert(associativeLaw(x, y, z))
      assert(identityLaw(x))
    }
    "String Set Monoid should hold monoid law" - {
      val x = Set("Hello", "World", "!")
      val y = x
      val z = x

      assert(associativeLaw(x, y, z))
      assert(identityLaw(x))
    }

  }
}