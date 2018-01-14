package chap06

import cats.syntax.apply._
import cats.data.Validated
import cats.data.Validated._
import cats.instances.list._

object FormValidation {
  type FormData = Map[String, String]
  type Errors = List[String]
  type ErrorOr[A] = Either[Errors, A]
  type AllErrorOr[A] = Validated[List[String], A]

  case class User(name: String, age: Int)

  def getValue(key: String)(data: FormData): ErrorOr[String] =
    data.get(key).toRight(List(s"$key is not valid key"))

  def parseInt(str: String): ErrorOr[Int] =
    scala.util.Try(str.toInt).toOption.toRight(List(s"$str is not able to parse"))

  def nonBlank(str: String): ErrorOr[String] =
    if (str.nonEmpty) Right(str) else Left(List(s"Empty is not allowed"))

  def nonNegative(num: Int): ErrorOr[Int] =
    if (num > 0) Right(num) else Left (List(s"$num is negative"))

  def readName(formData: FormData): ErrorOr[String] =
    getValue("name")(formData)
      .flatMap(nonBlank)

  def readAge(formData: FormData): ErrorOr[Int] =
    getValue("age")(formData)
      .flatMap(nonBlank)
      .flatMap(parseInt)
      .flatMap(nonNegative)

  def validate(formData: FormData): Validated[Errors, User] =
    (
      Validated.fromEither[Errors, String](readName(formData)),
      Validated.fromEither[Errors, Int](readAge(formData))
    ).mapN(User.apply)

}
