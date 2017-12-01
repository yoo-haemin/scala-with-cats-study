package chap01

import utest._
import PrintableSyntax._

object PrintableSpec extends TestSuite{
  val catName = "Kat"
  val age = 13
  val color = "blue"
  val cat = Cat(catName, age, color)

  val tests = Tests{
    "it should format cat" - {
      assert(Printable.format(cat) == s"${catName} is a ${age} year-old ${color} cat")
    }
    "it should format cat with syntax" - {
      assert(cat.format == s"${catName} is a ${age} year-old ${color} cat")
    }
  }
}