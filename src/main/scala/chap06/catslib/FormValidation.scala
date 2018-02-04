package chap06.catslib

import scala.language.implicitConversions
import scala.util.{ Try , Success, Failure }

import cats._
import cats.data.Validated
import cats.implicits._

case class User(name: String, age: Int)

object FormValidation {

  type AllErrorsOr[A] = Validated[List[String], A]

  implicit def tryToEither[A](t: Try[A]): Either[String, A] = t match {
    case Success(v) => Right(v)
    case Failure(e) => Left(e.toString)
  }

  private def getValue(data: Map[String, String], key: String): Either[String, String] =
    Try(data.apply(key))

  private def parseInt(s: String): Either[String, Int] =
    Try(s.toInt)

  private def nonBlank(s: String): Either[String, String] =
    if (!(s == "")) Right(s) else Left("Blank name")

  private def nonNegative(i: Int): Either[String, Int] =
    if(i > 0) Right(i) else Left("Negative or zero age")

  //Rules:
  //the name and age must be specified;
  //the name must not be blank;
  //the age must be a valid non-negative integer.
  def readName(data: Map[String, String]): Either[String, String] = {
    val name = getValue(data, "name")

    name flatMap nonBlank
  }

  def readAge (data: Map[String, String]): Either[String, Int] = {
    val age = getValue(data, "age")

    age flatMap parseInt flatMap nonNegative
  }

  def apply(data: Map[String, String]): Validated[List[String], User] = {
    val name = readName(data).toValidated.leftMap { _.pure[List] }
    val age = readAge(data).toValidated.leftMap { _.pure[List] }

    (name -> age) mapN User.apply
  }
}
