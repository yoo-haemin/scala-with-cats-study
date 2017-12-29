package chap04

import cats.Monad

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    leaf(value)

  implicit val TreeMonad: Monad[Tree] = new Monad[Tree] {
    override def pure[A](x: A): Tree[A] = Leaf(x)

    override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
      case Leaf(v) => f(v)
    }

    override def tailRecM[A, B](a: A)(f: A => Tree[Either[A, B]]): Tree[B] = {
      f(a) match {
        case Branch(l, r) => Branch(
          flatMap(l) {
            case Left(l) => tailRecM(l)(f)
            case Right(r) => pure(r)
          },
          flatMap(r) {
            case Left(l) => tailRecM(l)(f)
            case Right(r) => pure(r)
          })
        case Leaf(Left(b)) => tailRecM(b)(f)
        case Leaf(Right(b)) => leaf(b)
      }
    }


  }

}


