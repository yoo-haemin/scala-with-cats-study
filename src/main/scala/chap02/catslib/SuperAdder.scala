//Separate package to avoid colision with self defined Monoid
package chap02.catslib

import cats.kernel.Monoid
import cats.syntax.semigroup._
import cats.instances.int._
import cats.instances.option._

//Exercise 2.5.4
object SuperAdder {
  implicit val orderMonoid = new Monoid[Order] {
    override def combine(o1: Order, o2: Order) = Order(o1.totalCost + o2.totalCost, o1.quantity + o2.quantity)
    override def empty = Order(0.0, 0.0)
  }


  def add(items: List[Int]): Int =
    (Monoid[Int].empty /: items) { _ |+| _ }

  def addOpt(items: List[Option[Int]]): Option[Int] =
    (Monoid[Option[Int]].empty /: items) { _ |+| _ }

  def addOrder(items: List[Order]): Order =
    (Monoid[Order].empty /: items) { _ |+| _ }
}

case class Order(totalCost: Double, quantity: Double)

