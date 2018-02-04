package chap03

import utest._
import cats.syntax.functor._
import Tree.treeF

object TreeSpec extends TestSuite {
  val tree1: Tree[Int] = Branch(Leaf(1), Leaf(3))
  val tree2: Tree[String] = Branch(Leaf("1"), Leaf("3"))
  val tree3: Tree[Int] = Branch(Leaf(2), Leaf(4))

  val tests = Tests {
    "Map toString" - {
      assert(
        tree1.map(_.toString) == tree2
      )
    }

    "Map add 1" - {
      assert(
        tree1.map(_ + 1) == tree3
      )
    }
  }
}
