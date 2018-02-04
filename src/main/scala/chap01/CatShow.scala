package chap01

import cats.Show

//Exercise 1.4.6
object CatShow {
  implicit val catShow = new Show[Cat] {
    def show(c: Cat): String =
      s"${c.name} is a ${c.age} year-old ${c.color} cat"
  }
}

