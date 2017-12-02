package chap02

object BooleanOrMonoid {
  implicit val booleanOrMonoid: Monoid2[Boolean] = new Monoid2[Boolean] {
    def empty: Boolean = false

    def combine(x: Boolean, y: Boolean): Boolean = x || y
  }
}

object BooleanAndMonoid {
  implicit val booleanAndMonoid: Monoid2[Boolean] = new Monoid2[Boolean] {
    def empty: Boolean = true

    def combine(x: Boolean, y: Boolean): Boolean = x && y
  }
}
