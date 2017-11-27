package chap01

//Exercise 1.3
trait Printable[A] {
  def format(a: A): String
}

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    def format(s: String): String =
      s
  }

  implicit val intPrintable = new Printable[Int] {
    def format(i: Int): String =
      i.toString
  }
}

object Printable {
  def format[A](a: A)(implicit aPrintable: Printable[A]): String =
    aPrintable.format(a)

  def print[A](a: A)(implicit aPrintable: Printable[A]): Unit =
    println(format(a))
}

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit aPrintable: Printable[A]): String = Printable.format(a)

    def print(implicit aPrintable: Printable[A]): Unit = Printable.print(a)
  }
}
