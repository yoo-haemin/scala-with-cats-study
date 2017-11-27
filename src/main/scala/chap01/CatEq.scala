package chap01

import cats.Eq
import cats.syntax.eq._
import cats.instances.string._
import cats.instances.int._

//Exercise 1.5.5
object CatEq {
  implicit val catEq = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2.color
  }
}
