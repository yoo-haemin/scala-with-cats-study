package chap03

import cats.Functor

case class Box[A](value: A)

object Box {
  implicit val boxFunctor = new Functor[Box] {
    override def map[A, B](fa: Box[A])(f: A => B): Box[B] =
      Box(f(fa.value))
  }

  implicit def boxPrintable[A](implicit aPrintable: Printable[A]) = new Printable[Box[A]] {
    override def format(b: Box[A]): String =
      Printable.format(b.value)
  }
  
  implicit def boxCodec[A](implicit aCodec: Codec[A]) = 
    aCodec.imap(
      enc = { (box: Box[A]) => box.value },
      dec = { (a: A) => Box(a)}
    )
}

