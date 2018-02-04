package chap02

import cats.syntax.semigroup._
import catslib.Order
import catslib.SuperAdder.orderMonoid
import utest._

object OrderSpec extends TestSuite {
  val order1 = Order(100, 1)
  val order2 = Order(500, 4)

  val orderCombined = Order(600, 5)

  val tests = Tests {
    "Adding orders" - {
      assert((order1 |+| order2) == orderCombined)
    }
  }
}