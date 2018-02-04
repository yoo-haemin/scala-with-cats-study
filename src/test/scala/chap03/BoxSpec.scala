package chap03

import utest._
import Box._
import cats.syntax.functor._
import Printable._
import Codec._

object BoxSpec extends TestSuite {
  val originalBox = Box(1)
  val fun = (i: Int) => i + 1
  val newBox = Box(2)

  val testVal = true

  val tests = Tests {
    'Mapping - {
      assert(
        originalBox.map(fun) == newBox
      )
    }

    'Printing - {
      assert(
        format(Box(testVal)) == format(testVal)
      )
    }
    
    'Codecs - {
      assert(
        encode(Box(1.3)) == "1.3"
      )
      
      assert(
        decode[Box[Double]]("1.3") == Box(1.3)
      )
      
      assert(
        encode(Box(1.3)) == encode(1.3)
      )
      
      assert(
        decode[Box[Double]]("1.3") == Box(decode[Double]("1.3"))
      )
    }
  }
}
