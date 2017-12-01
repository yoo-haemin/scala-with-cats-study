package chap01

import utest._
import PrintableSyntax._

object PrintableSpec extends TestSuite{
  val tests = Tests{
    "it should show  cat" - {
      val catName = "Kat"
      val age = 13
      val color = "blue"
      val cat = Cat(catName, age, color)

      assert(Printable.format(cat) == s"${catName} is a ${age} year-old ${color} cat")
      assert(cat.format == s"${catName} is a ${age} year-old ${color} cat")
    }
  }
}