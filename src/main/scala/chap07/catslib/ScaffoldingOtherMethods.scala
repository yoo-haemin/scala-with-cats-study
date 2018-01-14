package chap07.catslib

import cats.Monoid
import cats.syntax.monoid._

object ScaffoldingOtherMethods {
  def map[A, B](xs: List[A])(f: A => B): List[B] =
    xs.foldRight(List.empty[B]) { (v, acc) => f(v) :: acc }

  def filter[A](xs: List[A])(f: A => Boolean): List[A] =
    xs.foldRight(List.empty[A]) { (v, acc) => if (f(v)) v :: acc else acc }

  def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] =
    xs.foldRight(List.empty[B]) { (v, acc) => f(v) ++ acc }

  def sum[A](xs: List[A])(implicit aMonoid: Monoid[A]): A =
    xs.foldRight(Monoid[A].empty) { (v, acc) => v |+| acc }
}
