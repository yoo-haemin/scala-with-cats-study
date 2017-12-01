package chap01

import utest._

object PrintableSpec extends TestSuite{
  val tests = Tests{
    "1 should equal 3" - {
      assert(1 == 3)
    }
    'test2 - {
      1
    }
    'test3 - {
      val a = List[Byte](1, 2)
      a(10)
    }
  }
}