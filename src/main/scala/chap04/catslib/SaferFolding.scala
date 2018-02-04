package chap04.catslib

import cats.Eval

// Exercise 4.6.5
// The naive implementation of foldRight below is not stack safe. 
// Make it so using Eval :
object SaferFolding {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    def loop(as: List[A], acc: Eval[B]): Eval[B] = 
      as match {
         case head :: tail =>
           Eval.defer {
             loop(tail, acc.map { b => fn(head, b) })
           }
         case Nil =>
           acc
      }

    loop(as, Eval.now(acc)).value
  }
}