package chap01

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catPrintable = new Printable[Cat] {
    override def format(c: Cat): String =
      s"${c.name} is a ${c.age} year-old ${c.color} cat"
  }
}
