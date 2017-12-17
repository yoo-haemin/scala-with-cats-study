package com.yoohaemin

import cats.free.Free

sealed trait KVStoreA[A]
case class Put[T](key: String, value: T) extends KVStoreA[Unit]
case class Get[T](key: String) extends KVStoreA[Option[T]]
case class Delete(key: String) extends KVStoreA[Unit]

object KVStore extends App {
  // Free your ADT
  // There are five basic steps to “freeing” the ADT:

  //Create a type based on Free[_] and KVStoreA[_].
  type KVStore[A] = Free[KVStoreA, A]

  //Create smart constructors for KVStore[_] using liftF.
  import cats.free.Free.liftF

  // Put returns nothing (i.e. Unit).
  def put[T](key: String, value: T): KVStore[Unit] =
    liftF[KVStoreA, Unit](Put[T](key, value))

  // Get returns a T value.
  def get[T](key: String): KVStore[Option[T]] =
    liftF[KVStoreA, Option[T]](Get[T](key))

  // Delete returns nothing (i.e. Unit).
  def delete(key: String): KVStore[Unit] =
    liftF(Delete(key))

  // Update composes get and set, and returns nothing.
  def update[T](key: String, f: T => T): KVStore[Unit] =
    for {
      vMaybe <- get[T](key)
      _ <- vMaybe.map(v => put[T](key, f(v))).getOrElse(Free.pure(()))
    } yield ()


  //Build a program out of key-value DSL operations.
  val program = for {
    _ <- put("yellowcat", 3)
    _ <- put("blackcat", 3)
    v <- get[Int]("yellowcat")
    _ <- update[Int]("asdf", ((a: Int) => a - 200))
  } yield v

  //Build a compiler for programs of DSL operations.
  import cats.arrow.FunctionK
  import cats.{Id, ~>}
  import scala.collection.mutable

  // the program will crash if a key is not found,
  // or if a type is incorrectly specified.
  def impureCompiler: KVStoreA ~> Id  =
    new (KVStoreA ~> Id) {

      // a very simple (and imprecise) key-value store
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

  //Execute our compiled program.
  val compiled = program.foldMap(impureCompiler)

  println(compiled)


  val result = program.foldMap(impureCompiler)
    //compiled.run(Map.empty).value
}
