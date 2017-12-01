package chap01

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._

final case class Cat(name: String, age: Int, color: String)

// Next we’ll create an implementa on of Printable for Cat that returns con-
// tent in the following format:
// NAME is a AGE year-old COLOR cat.

object Cat {
  implicit object PrintableCat extends Printable[Cat] {
    def format(cat: Cat): String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }
  
  implicit object ShowCat extends Show[Cat] {
    def show(cat: Cat): String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }

  implicit val CatEq: Eq[Cat] = Eq.instance { (cat1, cat2) =>
    cat1.name === cat2.name
    cat1.age === cat2.age &&
    cat1.color === cat2.color
  }
}

