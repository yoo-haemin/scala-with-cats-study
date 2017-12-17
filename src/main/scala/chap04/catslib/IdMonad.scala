package chap04.catslib

import cats.Id
import chap04.Monad

object IdMonad {
  implicit val myIdMonad = new Monad[Id] {
    override def pure[A](a: A): Id[A] = 
      a

    override def flatMap[A,B](a: A)(f: A => Id[B]): Id[B] =
      f(a)
  }
}