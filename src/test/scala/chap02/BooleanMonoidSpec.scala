package chap02

import utest._
import Monoid2._

object BooleanMonoidSpec extends TestSuite{
  def lawTester(m: Monoid2[Boolean]): Boolean = {
    implicit val monoid = m
    assert(associativeLaw(true, true, true))
    assert(associativeLaw(true, true, false))
    assert(associativeLaw(true, false, true))
    assert(associativeLaw(false, true, true))
    assert(associativeLaw(true, false, false))
    assert(associativeLaw(false, false, true))
    assert(associativeLaw(false, true, false))
    assert(associativeLaw(false, false, false))
    assert(identityLaw(true))
    assert(identityLaw(false))
    return true
  }

  val tests = Tests{
    "Or Monoid should hold monoid law" - {
      assert(lawTester(BooleanOrMonoid.booleanOrMonoid))
    }
    "And Monoid should hold monoid law" - {
      assert(lawTester(BooleanAndMonoid.booleanAndMonoid))
    }
  }
}