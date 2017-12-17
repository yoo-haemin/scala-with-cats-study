package chap02

import utest._
import SuperAdder._
import SuperPos._

object SuperPosSpec extends TestSuite{
  val tests = Tests{
    "It should add all order" - {
      assert(add(List(Order(10, 20), Order(20, 30))) == Order(30, 50))
    }
  }
}