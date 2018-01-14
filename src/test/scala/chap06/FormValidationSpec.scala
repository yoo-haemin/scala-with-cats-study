package chap06

import utest._
import FormValidation._
import cats.data.Validated.{Valid, Invalid}

object FormValidationSpec extends TestSuite {
  val tests = Tests {

    "it should validate form" - {
      val formData = Map("name" -> "John Doe", "age" -> "30")

      assert(readName(formData) == Right("John Doe"))
      assert(readAge(formData) == Right(30))
      assert (validate(formData) == Valid(User("John Doe",30)))
      assert(validate(Map("name" -> "", "age" -> "-100")) == Invalid(List("Empty is not allowed", "-100 is negative")))
    }
  }
}
