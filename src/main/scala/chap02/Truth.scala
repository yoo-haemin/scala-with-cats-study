package chap02

//Exercise 2.3
object Truth {
  implicit val and = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = a && b
    def empty = true
  }

  implicit val or = new Monoid[Boolean] {
    def combine(a: Boolean, b: Boolean): Boolean = a || b
    def empty = false
  }
}
