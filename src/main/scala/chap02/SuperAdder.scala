package chap02

import cats.Monoid
import cats.syntax.semigroup._

object SuperAdder {
  def add[A](items: List[A])(implicit m: Monoid[A]) = items.foldLeft(m.empty)(_ |+| _)
}
