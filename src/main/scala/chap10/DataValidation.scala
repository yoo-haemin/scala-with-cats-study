package chap10

import cats._
import cats.implicits._

object DataValidation {
  // implicit def checkSG[E, A] = new Semigroup[Check[E, A]] {
  //   override def combine(a: Check[E, A], b: Check[E, A]): Check[E, A] = a and b
  // }

  trait Check[E, A] extends (A => Either[E, A]) { self =>
    def and(that: Check[E, A])(implicit eSG: Semigroup[E]): Check[E, A] = {
      new Check[E, A] {
        override def apply(a: A): Either[E, A] = {
          self.apply(a) match {
            case Right(a) => that(a)
            case Left(e1) =>
              that(a) match {
                case Right(_) => Left(e1)
                case Left(e2) => Left(e1 |+| e2)
              }
          }
        }
      }
    }

    //def or(that: Check[E, A])
    def apply(value: A): Either[E, A]
  }
}
