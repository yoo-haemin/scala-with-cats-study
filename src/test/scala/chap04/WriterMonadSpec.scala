package chap04

import utest._
import WriterMonad._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object WriterMonadSpec extends TestSuite {
  val tests = Tests {
    "it should log factorial" - {
//      val (log, res) = factorial2(5).run
//      print(log, res)

      Await.result(Future.sequence(Vector(
        Future(factorial(3)),
        Future(factorial(3))
      )), 5.second )
    }
  }
}
