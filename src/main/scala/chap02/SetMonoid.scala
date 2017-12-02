package chap02

object SetMonoid {
  implicit def setMonoid[A]: Monoid2[Set[A]] = new Monoid2[Set[A]] {
    def empty: Set[A] = Set[A]()

    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }
}
