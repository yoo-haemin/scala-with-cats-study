package chap02

import cats.Monoid

case class Order(totalCost: Double, quantity: Double)

object SuperPos {
  implicit val superPosMonoid: Monoid[Order] = new Monoid[Order] {
    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)

    override def empty: Order = Order(0, 0)
  }
}
