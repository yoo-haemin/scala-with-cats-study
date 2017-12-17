package chap03

trait Printable[A] {
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = {
    val aFormat = format _

    new Printable[B] {
      def format(value: B): String = aFormat(func(value))
    }
  }
}

object Printable {
  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }
  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit val intPrintable: Printable[Int] =
    stringPrintable.contramap(i => i.toString)

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)
}

