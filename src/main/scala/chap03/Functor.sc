//import cats._


//trait Functor[F[_]] {
//  def map[A, B](v: F[A])(f: A => B): F[B]
//
//  def lift[A, B](f: A => B): F[A] => F[B] =
//    v => map(v)(f)
//}
//
//
//val a = List(1, 2, 3)
//
//val func = (x: Int) => x + 1
//

object A {
  import cats.Monad
  
  import cats.syntax.flatMap._
  //import cats.syntax.applicative._
  
  import cats.instances.option._
  
  
  val mo = implicitly[Monad[Option]]
  
  val a = mo.flatMap(Some(1))(i => Some(i + 1))
  
  Some(1).flatMap(i => Some(i+1))
}