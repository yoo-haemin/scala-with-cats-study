package chap01

import utest._

//Exercise 1.4.6
object CatShowSpec extends TestSuite {
  import cats.Show
  import CatShow._

  val exampleCat = Cat("Cacat", 1000, "mysterious")
  val exampleCatFormatted = "Cacat is a 1000 year-old mysterious cat"

  val showCat = Show[Cat]

  val tests = Tests {
    "CatsPrintable" - {
      "should be able to properly format cats" - {
        assert(
          showCat.show(exampleCat) == exampleCatFormatted
        )
      }
    }

    "CatsPrintable with PrintableSyntax" - {
      import cats.syntax.show._

      "should be able to properly format cats" - {
        assert(
          exampleCat.show == exampleCatFormatted
        )
      }
    }
  }
}
