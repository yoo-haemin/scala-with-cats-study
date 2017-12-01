package chap01

import utest._
import ShowSyntax._

object ShowSpec extends TestSuite{
  val tests = Tests{
    "it should show  cat" - {
      val catName = "Kat"
      val age = 13
      val color = "blue"
      val cat = Cat(catName, age, color)

      assert(Show.show(cat) == s"${catName} is a ${age} year-old ${color} cat")
      assert(cat.show == s"${catName} is a ${age} year-old ${color} cat")
    }
  }
}