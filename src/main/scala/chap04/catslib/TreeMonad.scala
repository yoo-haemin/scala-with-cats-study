package chap04.catslib

import chap03.{ Tree, Branch, Leaf }
import cats.Monad

object TreeMonad {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)


  implicit val treeMonad = new Monad[Tree] {
    override def flatMap[A, B](tree: Tree[A])(fn: A => Tree[B]): Tree[B] =
      tree match {
        case Branch(l, r) => branch(flatMap(l)(fn), flatMap(r)(fn))
        case Leaf(a) => fn(a)
      }

    override def pure[A](value: A): Tree[A] = Leaf(value)

    //@annotation.tailrec
    override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] = f(a) match {
      case Branch(lEither, rEither) =>
        import cats.Eval

        //@annotation.tailrec
        //def loop(a: A, acc: Eval[Tree[B]]) = {}

        val value = for {
          left <- Eval.now(flatMap(lEither)(_.fold(a => tailRecM(a)(f), b => pure(b))))
          right <- Eval.now(flatMap(lEither)(_.fold(a => tailRecM(a)(f), b => pure(b))))
        } yield branch(left, right)

        value.value

      case Leaf(Left(a)) => tailRecM(a)(f)
      case Leaf(Right(b)) => Leaf(b)
    }
  }
}
