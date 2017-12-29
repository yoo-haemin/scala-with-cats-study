package chap04.catslib

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.writer._
import cats.syntax.applicative._

object ShowYourWorking {
  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorialExample(n: Int): Int = {
    val ans = slowly(if(n == 0) 1 else n * factorialExample(n - 1))
    println(s"fact $n $ans")
    ans
  }

  // Rewrite factorial so it captures the log messages in a Writer.
  // Demonstrate that this allows us to reliably separate the logs
  // for concurrent computations.
  def factorial(n: Int): Writer[Vector[String], Int] = {
    def loop(n: Int, acc: Writer[Vector[String], Int]): Writer[Vector[String], Int] = {
      val (log, result) = acc.run
      val accv = slowly(if(n == 0) 1 else result)

      if (n == 0)
        acc
      else
        loop(
          n - 1,
          acc.bimap(
            { v => println(v); s"fact $n $accv" +: v},
            (_ => accv)
          ))
    }

    loop(n, Writer(Vector.empty[String], 1))
  }
}

object WriterExample {
  type Logged[A] = Writer[Vector[String], A]

  123.pure[Logged]

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  10.pure[Logged] flatMap { a =>
    Vector("a", "b", "c").tell flatMap { b =>
      32.writer(Vector("x", "y", "z"))    }
  }
}
