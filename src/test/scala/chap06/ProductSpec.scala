package chap06

import utest._
import Product._
import cats.instances.list._


object ProductSpec extends TestSuite {
  val tests = Tests {
    "it should product Semigroupal" - {
      product(List(1, 2), List(3, 4))
    }
  }
}