package chap01

trait Show[A] {
  def show(value: A): String
}

object ShowInstance {
  implicit val showString: Show[String] = new Show[String] {
    def show(value: String): String = value
  }

  implicit val showInt: Show[Int] = new Show[Int] {
    def show(value: Int): String = value.toString
  }
}

object Show {
  def show[A](value: A)(implicit s: Show[A]) = s.show(value)
}

object ShowSyntax {
  implicit class ShowOps[A](value: A) {
    def show(implicit s: Show[A]): String = s.show(value)
  }
}