package chap04

import cats.data.Reader
import cats.syntax.applicative._


object LoginSystem {

  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  // map.get(key): Option[_] == password 를 했는데 타입 에러가 안남

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      check <- username.map(username => checkPassword(username, password)).getOrElse(false.pure[DbReader])
    } yield check

  def checkLogin2(userId: Int, password: String): DbReader[Boolean] =
    findUsername(userId) flatMap { username =>
      username match {
        case Some(username) => checkPassword(username, password)
        case None => false.pure[DbReader]
      }
    }
}
