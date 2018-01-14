package chap05

import scala.concurrent.{Await, Future}
import cats.data.EitherT
import cats.Monad
import cats.syntax.applicative._
import cats.instances.future._
import scala.concurrent.Await._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


object Transformer {
  implicit val futureM = Monad[Future]
  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map (
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = {
    powerLevels.get(autobot) match {
      case Some(level) => level.pure[Response]
      case None => EitherT[Future, String, Int](Future(Left("Autobot unreachable")))
    }
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
    getPowerLevel(ally1) flatMap(a1 => {
      getPowerLevel(ally2) map (a2 => (a1 + a2) > 15)
    })
  }

  def tacticalReport(ally1: String, ally2: String): String = {

    Await.result(canSpecialMove(ally1, ally2).value, 1.second) match {
      case Right(true) => s"$ally1 and $ally2 are ready to roll out!"
      case Right(false) => s"$ally1 and $ally2 need to a recharge"
      case Left(a) => s"Cooms error: $a"
    }
  }
}
