package chap06

import cats.data.Validated
import cats.syntax.validated._

object Registration {
  type FormData = Map[String, String]
  type Errors = List[String]
  type ErrorOr[A] = Validated[Errors, A]

  case class User(name: String, age: Int)

  def getValue(map: FormData)(value: String): ErrorOr[String] =
    map.get(value)
      .fold(List("Invalid Field").invalid[String])(_.valid[Errors])

  def parseInt(str: String): ErrorOr[Int] =
    scala.util.Try(str.toInt).toEither match {
      case Left(_) => List("Not a number").invalid[Int]
      case Right(age) => age.valid[Errors]
    }
  def nonBlack(str: String): Boolean = str.trim.length > 0

  def nonNegative(num: Int): Boolean = num > 0

  def readName(formData: FormData): ErrorOr[String] = getValue(formData)("name").ensure(List("Black!"))(nonBlack(_))

  // def readAge(formData: FormData): ErrorOr[Int] =
  //  getValue(formData)("age").map(parseInt(_)).ensure(List("Not a positive number"))(nonNegative(_))


  def readName0(form: Map[String, String]): Either[List[String], String] = {
    form.get("name").fold(List("Invalid Name").invalid[String])(_.valid[List[String]]).toEither
  }

  def readAge0(form: Map[String, String]): Either[List[String], Int] = {
    form.get("age").fold(List("Invalid Age").invalid[Int]){
      age => scala.util.Try(age.toInt).toEither match {
        case Left(_) => List("Invalid Age").invalid[Int]
        case Right(age) => age.valid[List[String]].ensure(List("Negative Number"))(_ > 0)
      }
    } toEither
  }
}
