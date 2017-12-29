package chap04

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

object WriterMonad {
  type Logged[A] = Writer[Vector[String], A]

  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    def fac(n: Int): Writer[Vector[String], Int] = {
      if (n == 0) {
        Writer(Vector("fact 0 1"), 1)
      } else {
        val ans = slowly(fac(n - 1))
        ans.bimap(_ :+ s"fact $n ${ans.value * n}", _ * n)
      }
    }

    val res = fac(n)
    println(res.written)
    res.value
  }

  def factorial2(n: Int): Logged[Int] =
    for {
      ans <- if (n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial2(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

}
