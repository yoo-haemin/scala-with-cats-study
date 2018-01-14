package chap06.catslib

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

object ProductOfMonads {
  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
    //This looks prettier in my opinion
    //for {
    //  xv <- x
    //  yv <- y
    //} yield x -> y
    x.flatMap { xv =>
      y.map { yv =>
        xv -> yv
      }
    }
}
