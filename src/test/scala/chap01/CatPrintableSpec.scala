package chap01

import utest._

object CatPrintableSpec extends TestSuite {
  import Printable._
  import Cat._

  val exampleCat = Cat("Cacat", 1000, "mysterious")
  val exampleCatFormatted = "Cacat is a 1000 year-old mysterious cat"

  val tests = Tests {
    //Exercise 1.3
    "CatsPrintable" - {
      "should be able to properly format cats" - {
        assert(
          format(exampleCat) == exampleCatFormatted
        )
      }
    }

    "CatsPrintable with PrintableSyntax" - {
      import PrintableSyntax._

      "should be able to properly format cats" - {
        assert(
          exampleCat.format == exampleCatFormatted
        )
      }
    }
  }
}
