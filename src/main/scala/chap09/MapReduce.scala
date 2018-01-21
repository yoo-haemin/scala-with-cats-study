package chap09

// our parallel fold will yield the correct results if:
// we require the reducer function to be associative;
// we seed the computation with the identity of this function.

import cats._
import cats.implicits._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object MapReduce {
  import cats.free.Free
  import java.util.Scanner

  def foldMap[A, B: Monoid, F[_]:Foldable:Traverse](xs: F[A])(f: A => B): B = {
    xs.map(f).foldl(Monoid[B].empty) { _ |+| _ }
  }

  def parallelFoldMap[A, B : Monoid](xs: Vector[A])(f: A => B): Future[B] = {
    val batchSize = {
      if (xs.length % Runtime.getRuntime.availableProcessors() == 0)
        xs.length / Runtime.getRuntime.availableProcessors()
      else
        xs.length / Runtime.getRuntime.availableProcessors() + 1
    }

    Future.sequence {
      xs.grouped(batchSize)
        .map { v => Future(foldMap(v)(f)) }
    }.map { v => v.foldLeft(Monoid[B].empty)(_ |+| _) }
  }

  // def parallelFoldMapWithCats[A, B: Monoid, F[_]:Foldable:Traverse](xs: F[A])(f: A => B): Future[B] = {
  //   def grouped(xs: F[A])(size: Int): F[F[A]] = {
  //     xs.zipWithIndex.foldr(Eval.now(List.empty[A] :: Nil)) {
  //       case ((x, idx), xs: Eval[List[List[A]]]) =>
  //         ???
  //     }
  //     ???
  //   }
  //   val batchSize = {
  //     val processorCount = Runtime.getRuntime.availableProcessors()
  //     xs.size
  //     1
  //   }
  //   val a = grouped(xs)(batchSize).map { ys =>
  //     Future(ys foldMap f)
  //   }
  //   ???
  // }
}
