//import cats._


trait Functor[F[_]] {
  def map[A, B](v: F[A])(f: A => B): F[B]

  def lift[A, B](f: A => B): F[A] => F[B] =
    v => map(v)(f)
}


val a = List(1, 2, 3)

val func = (x: Int) => x + 1

