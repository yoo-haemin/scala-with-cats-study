package chap01

import utest._

import cats.syntax.eq._
import cats.instances.option._
import CatEq._

//Exercise 1.5.5
object CatEqSpec extends TestSuite {

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  val tests = Tests {
    "CatsEq" - {
      "should be able to properly check equality between cats" - {
        * - assert(cat1 === cat1)
        * - assert(cat1 =!= cat2)
      }

      "should be able to properly check equality in the presence of options" - {
        * - assert(optionCat1 === optionCat1)
        * - assert(optionCat1 =!= optionCat2)
      }
    }
  }
}
