package chap01


// 1. DefineatypeclassPrintable[A]containingasinglemethodformat.
// format should accept a value of type A and return a String.

// 타입 클래스, 모양 정의
trait Printable[A] {
  def format(value: A): String
}

// 2. Create an object PrintableInstances containing instances of
// Printable for String and Int.

// 타입 클래스의 인스턴스, 행동 정의
// In Scala we define instances by creating concrete implementaions of the type class and tagging them with the implicit keyword:
// 타임 클래스 구현하고 implicit을 붙여서 인스턴스를 만든다.
object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val intPrintable = new Printable[Int] {
    def format(value: Int): String = value.toString
  }
}

// 3. Define an object Printable with two generic interface methods:
// format accepts a value of type A and a Printable of the correspond- ing type. It uses the relevant Printable to convert the A to a String.
// print accepts the same parameters as format and returns Unit. 
// It prints the A value to the console using println.

// 타입 클래스의 인터페이스
// A type class interface is any funcionality we expose to users. Interfaces are generic methods that accept instances of the type class as implicit parameters.
// 타입 클래스의 인터페이스는 사용자가 쓰는 함수임. 2번에서 만든 타입 클래스의 인스턴스를 implicit 으로 받음
object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  def printer[A](value: A)(implicit p: Printable[A]): Unit = println(p.format(value))
}


// 1. CreateanobjectcalledPrintableSyntax.
// 2. Inside PrintableSyntax define an implicit class Print-
// ableOps[A] to wrap up a value of type A.
// 3. InPrintableOpsdefinethefollowingmethods:
// • format accepts an implicit Printable[A] and returns a String representa on of the wrapped A;
// // Define a cat:
// val cat = Cat(/* ... */)
// // Print the cat!
// 20 CHAPTER1. INTRODUCTION • print accepts an implicit Printable[A] and returns Unit. It
// prints the wrapped A to the console.
// 4. UsetheextensionmethodstoprinttheexampleCatyoucreatedinthe
// previous exercise.

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = p.printer(value)
  }
}