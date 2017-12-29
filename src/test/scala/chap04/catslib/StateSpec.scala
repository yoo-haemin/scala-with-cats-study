package chap04.catslib

import utest._
import PostOrderCalculator._
import cats.data.NonEmptyList

object StateSpec extends TestSuite {
  val tests = Tests {
    'evalOne - {
      assert(evalOne("42").runA(Nil).value == 42)

      assert(evalOne("+").runA(List(1, 2)).value == 3)

      assert {
        (for {
           _ <- evalOne("42")
           _ <- evalOne("41")
           a <- evalOne("-")
         } yield a).runA(Nil).value == 1
      }
    }

    'evalAll - {
      val a = evalAll(NonEmptyList.of("1", "2", "+")).runA(Nil).value
      assert(a == 3)

      val program = evalAll(NonEmptyList.of("1", "2", "+", "3", "-")).runA(Nil).value
      assert(program == 0)
    }
  }
}
