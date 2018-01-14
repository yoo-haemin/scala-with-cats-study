package chap06

import cats.Monad
import cats.syntax.functor._
import cats.syntax.flatMap._

object  Product {
  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
    for {
      x <- x
      y <- y
    } yield (x, y)
}
