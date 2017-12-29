package chap04.catslib

import cats.data.Reader
import cats.syntax.applicative._

object HackingOnReaders {
  case class Db(
    usernames: Map[Int, String],
    passwords: Map[String, String]
  )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader { db =>
      db.usernames.get(userId)
    }

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader { db =>
      db.passwords.get(username).map(_ == password).getOrElse(false)
    }

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    findUsername(userId) flatMap { usernameOpt =>
      usernameOpt match {
        case Some(username) =>
          checkPassword(username, password)
        case _ =>
          false.pure[DbReader]
      }
    }
}
