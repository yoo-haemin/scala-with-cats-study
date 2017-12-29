package chap04

import utest._
import PostOrderCalc._

object PostOrderCalcSpec extends TestSuite {
  val tests = Tests {
    "it should eval one" - {
      val program = for {
        _ <- evalOne("1")
        _ <- evalOne("2")
        ans <- evalOne("+")
      } yield ans
      assert(program.runA(Nil).value == 3)
    }
    "it should eval all" - {
      val program2 = evalAll(List("1", "2", "+"))
      assert(program2.runA(Nil).value == 3)
    }
    "it should eval input" - {
      assert(evalInput("1 2 +") == 3)
    }
  }
}
