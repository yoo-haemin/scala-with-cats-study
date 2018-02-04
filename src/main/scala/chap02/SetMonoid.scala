package chap02

import scala.collection.immutable.Set

//Exercise 2.4
object SetMonoid {
  implicit def union[A] = new Monoid[Set[A]] {
    def combine(x: Set[A], y: Set[A]) = x ++ y
    def empty = Set.empty[A]
  }
}
