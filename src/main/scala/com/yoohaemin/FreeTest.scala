package com.yoohaemin
  //Just typing in the tutorials from https://typelevel.org/cats/datatypes/freemonad.html

//Create an ADT representing your grammar
sealed trait KVStoreA[A]
case class Put[T](key: String, value: T) extends KVStoreA[Unit]
case class Get[T](key: String) extends KVStoreA[Option[T]]
case class Delete(key: String) extends KVStoreA[Unit]

//Free your ADT
/*
 There are five basic steps to "freeing" the ADT:

 1. Create a type based on Free[_] and KVStoreA[_].
 2. Create smart constructors for KVStore[_] using liftF.
 3. Build a program out of key-value DSL operations.
 4. Build a compiler for programs of DSL operations.
 5. Execute our compiled program.
 */
object FreeTest extends App {
  import cats.free.Free

  // 1. Create a `Free` type based on your ADT
  type KVStore[A] = Free[KVStoreA, A]

  // 2. Create smart constructors using liftF
  import cats.free.Free.liftF

  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreA, Unit](Put[T](key, value))

  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreA, Option[T]](Get[T](key))

  def delete(key: String): KVStore[Unit] =
    liftF(Delete(key))

  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      vMaybe <- get[T](key)
      _ <- vMaybe.map { v => put[T](key, f(v)) }.getOrElse(Free.pure(()))
    } yield ()

  // 3. Build a program
  def program: KVStore[Option[Int]] =
    for {
      _ <- put("wild-cats", 2)
      _ <- update[Int]("wild-cats", (_ + 12))
      _ <- put("tame-cats", 5)
      n <- get[Int]("wild-cats")
      _ <- delete("tame-cats")
    } yield n

  println(program) //This thing doesn't print!

  // 4. Write a compiler for your program
  import cats.arrow.FunctionK
  import cats.{ Id, ~> }
  import scala.collection.mutable


  def impureCompiler: KVStoreA ~> Id =
    new (KVStoreA ~> Id) {
      val kvs = mutable.Map.empty[String, Any]

      def apply[A](fa: KVStoreA[A]): Id[A] =
        fa match {
          case Put(key, value) =>
            println(s"put($key, $value)")
            kvs(key) = value
            ()

          case Get(key) =>
            println(s"get($key)")
            kvs.get(key).map(_.asInstanceOf[A])

          case Delete(key) =>
            println(s"delete($key)")
            kvs.remove(key)
            ()
        }
    }

  def printer: KVStoreA ~> Id =
    new (KVStoreA ~> Id) {
      def apply[A](fa: KVStoreA[A]): Id[A] =
        fa match {
          case p@Put(key, value) =>
            println(p)
          case g@Get(key) =>
            println(g)
            null
          case d@Delete(key) =>
            println(d)
        }
    }

  println(program.foldMap(printer))
}
