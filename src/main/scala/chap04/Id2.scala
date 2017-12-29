package chap04

import cats.Id

object IdMonad extends Monad2[Id] {
  def pure[A](x: A): Id[A] = x

  def flatMap[A, B](fa: Id[A])(f: (A) => Id[B]): Id[B] = f(fa)
}