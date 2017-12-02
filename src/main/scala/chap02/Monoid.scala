package chap02

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid2[A] extends Semigroup[A] {
  def empty: A
}

object Monoid2 {
  def apply[A](implicit monoid: Monoid2[A]) =
    monoid

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid2[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }
  def identityLaw[A](x: A)(implicit m: Monoid2[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }
}