package chap01

final case class Cat(name: String, age: Int, color: String)

//Exercise 1.3
object Cat {
  implicit val catPrintable = new Printable[Cat] {
    def format(c: Cat): String =
      s"${c.name} is a ${c.age} year-old ${c.color} cat"
  }
}
