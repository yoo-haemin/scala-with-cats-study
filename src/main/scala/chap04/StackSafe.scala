package chap04

import cats.Eval

object StackSafe {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {

    def lazyFoldRight(as: List[A], acc: Eval[B]): Eval[B] = as match {
      case head :: tail =>
         Eval.defer(lazyFoldRight(tail, acc).map(b => fn(head, b)))
      case Nil =>
        acc
    }

    lazyFoldRight(as, Eval.later(acc)).value
  }
}