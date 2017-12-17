package chap03

import utest._
import Printable._
import Box._
import Codec._

object BoxSpec extends TestSuite {
  val tests = Tests{
    "It should format int box" - {
      assert(format(Box(true)) == "yes")
    }
    "It should format string box" - {
      assert(format(Box("Hello")) == "\"Hello\"")
    }

    "It should imap Codec[String]" - {
      assert(intCodec.isInstanceOf[Codec[Int]])
      assert(booleanCodec.isInstanceOf[Codec[Boolean]])
      assert(doubleCodec.isInstanceOf[Codec[Double]])
    }

    "It should imap Codec[Box[A]]" - {
      val intBoxCodec = encode(Box(123))
      assert(intBoxCodec == "123")
      assert(decode[Box[Int]](intBoxCodec).value == 123)
      assert(decode[Int](encode(123)) == 123)
    }
  }
  }

