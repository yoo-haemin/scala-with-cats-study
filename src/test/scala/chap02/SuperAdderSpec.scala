package chap02

import utest._
import SuperAdder._
import cats.instances.int._
import cats.instances.option._

object SuperAdderSpec extends TestSuite{
  val tests = Tests{
    "It should add all int items in list" - {
      assert(add(List(1,2,3,4,5)) == 1+2+3+4+5)
    }
    "It should add all int items in list" - {
      assert(add(List(Option(10), Option(30))) == Option(10+30))
      assert(add(List(Option(10), Option(30))) != Option(100+300))
    }
  }
}