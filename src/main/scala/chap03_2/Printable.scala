package chap03_2

trait Printable[A] {
  self =>

  def format(value: A): String

  def contramap[B](func: B => A) = new Printable[B] {
    def format(value: B): String = self.format(func(value))
  }
}

object Printable {
  implicit val stringPrintable = new Printable[String] {
    def format(value: String): String = "\"" + value+ "\""
  }

  implicit val booleanPrintable = new Printable[Boolean] {
    def format(value: Boolean): String = if (value) "yes" else "no"
  }
}