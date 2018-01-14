package chap07

import cats.Monoid
import cats.syntax.monoid._


object FoldRight2 {
  // map, flatMap, filter, and sum
  def map[A, B](l: List[A])(fa: A => B) =
    l.foldRight(List[B]())(fa(_) :: _)

  def flatMap[A, B](l: List[A])(fa: A => List[B]) =
    l.foldRight(List[B]())(fa(_) ++ _)

  def filter[A](l: List[A])(pred: A => Boolean) =
    l.foldRight(List[A]())( (v, acc) =>  if (pred(v)) v :: acc else acc )

  def sum[A](l: List[A])(implicit m: Monoid[A]) =
    l.foldRight(m.empty)(_ |+| _)
}
