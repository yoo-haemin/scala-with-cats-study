package chap03_2

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](tree: Tree[A])(func: A => B): Tree[B] = {
      tree match {
        case Branch(l, r) => Branch(map(l)(func), map(r)(func))
        case Leaf(v) => Leaf(func(v))
      }
    }
  }
}