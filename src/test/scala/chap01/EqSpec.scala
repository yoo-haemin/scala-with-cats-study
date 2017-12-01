package chap01

import utest._
import Cat._
import cats.syntax.eq._
import cats.instances.option._

object EqSpec extends TestSuite{
  val tests = Tests{
    "it should compare cat" - {
      val cat1 = Cat("Garfield",   38, "orange and black")
      val cat2 = Cat("Heathcliff", 33, "orange and black")

      assert(cat1 === cat1)
      assert(cat1 =!= cat2)
    }
    "it should compare option cat" - {
      val cat1 = Cat("Garfield",   38, "orange and black")
      val optionCat1 = Option(cat1)
      val optionCat2 = Option.empty[Cat]

      assert(optionCat1 =!= optionCat2)
    }
  }
}