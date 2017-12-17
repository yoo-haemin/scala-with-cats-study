package chap04

import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](a: A): F[A]
  
  def flatMap[A,B](value: F[A])(func: A => F[B]): F[B]

  //Exercise 4.1.2
  def map[A,B](value: F[A])(func: A => B): F[B] = 
    flatMap(value)(func andThen pure)
}
