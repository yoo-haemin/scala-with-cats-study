package chap04.catslib

import cats.Eval

//Exercise 4.6.5
object SaferFolding {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = ???
    //as match {
    //  case head :: tail =>
    //    fn(head, foldRight(tail, acc)(fn))
    //  case Nil =>
    //    acc
    //} 
}