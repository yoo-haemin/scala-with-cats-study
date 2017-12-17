package chap03

import cats.Functor

final case class Box[A](value: A)

object Box {
  //format 메서드를 가지고 있는 implicit Printable 오브젝트가 필요하다.
  // 박스 안에 있는 것을 출력해야함 즉 출력은 박스 안에 있는 값의 타입인 A
  // 입력값의 타입은 Box[A]
  implicit def boxPrintable[A](implicit p: Printable[A]) = p.contramap[Box[A]](_.value)
  // 질문1 왜 contramap 메서드는 스태틱 클래스의 메서드처럼 접근이 안 되는가
  // 질문2 왜 Printable[A]를 implicit 으로 받는가 => 박스 안에 있는 값인 A를 format해서 출력해야함.
  // 그래서 contramap을 통해 Box를 없애고 A만 남겨서 format 메서드 재사용

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] = c.imap[Box[A]](Box(_), _.value)

}

object BoxSyntax {
  implicit val boxFunctor: Functor[Box] = new Functor[Box] {
    def map[A, B](fa: Box[A])(f: (A) => B): Box[B] = Box(f(fa.value))
  }
}
