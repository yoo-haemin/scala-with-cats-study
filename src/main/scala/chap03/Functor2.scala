package chap03

import scala.language.higherKinds

trait Functor2[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def lift[A, B](fa: A => B): F[A] => F[B] =  v => map(v)(fa)
}
