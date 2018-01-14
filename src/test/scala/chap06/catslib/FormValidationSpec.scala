package chap06.catslib

import utest._
import cats.data._
import cats.implicits._

object FormValidationSpec extends TestSuite {
  val wrongData = List(
    Map("name" -> "asdf", "age" -> "0"),
    Map("name" -> "", "age" -> "0"),
    Map("name" -> "", "age" -> "asdf"),
    Map("age" -> "1"),
    Map("name" -> "asdf"),
    Map.empty[String, String]
  )

  val correctData = List(
    Map("name" -> "asdf", "age" -> "133")
  )

  val ageErr = "Negative or zero age"
  val blankErr = "Blank Name"
  val numberFormatException = """java.lang.NumberFormatException: For input string: "asdf""""
  val nameNotFound = """java.util.NoSuchElementException: key not found: name"""
  val ageNotFound = """java.util.NoSuchElementException: key not found: age"""

  val tests = Tests {
    'wrongData - {
      import Validated.{ Valid, Invalid }

      assert {
        val wrongResult =
          List[Validated[List[String], User]](
            Invalid(List(ageErr)),
            Invalid(List(blankErr, ageErr)),
            Invalid(List(blankErr, numberFormatException)),
            Invalid(List(nameNotFound)),
            Invalid(List(ageNotFound)),
            Invalid(List(nameNotFound, ageNotFound))
          )

        wrongData.map { d => val res = FormValidation(d); res } == wrongResult
      }
    }

    'correct - {
      wrongData.map { d => FormValidation(d) } ==
        List[Validated[List[String], User]](
          User("asdf", 133).pure[({ type L[A] = Validated[List[String], A] })#L]
        )
    }
  }
}
