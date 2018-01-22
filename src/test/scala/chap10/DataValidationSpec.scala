package chap10

import cats._
import cats.implicits._
import utest._
import DataValidation._

object DataValidationSpec extends TestSuite {
  val badness = (0 until 2).map(i => List(s"badness$i"))

  val alwaysFail = badness.map { b =>
    new Check[List[String], String] { def apply(a: String) = Left(b) }
  }

  val tests = Tests {
    'basic - {
      * - assert {
        val failure = alwaysFail(0)("hi")
        val left: Either[List[String], String] = Left(badness(0))
        failure === left
      }

      * - assert {
        val failure = alwaysFail(1)("hihi")
        val left: Either[List[String], String] = Left(badness(1))
        failure === left
      }
    }

    'and - {
      val fails = (alwaysFail(0) and alwaysFail(1))
      val left = Left(badness(0) |+| badness(1))
      val fail = fails("hihi")
      fails("hihi") === left
    }

    'or - {

    }
  }
}
