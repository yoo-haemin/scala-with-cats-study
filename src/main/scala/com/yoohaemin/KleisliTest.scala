package com.yoohaemin

import cats._
import cats.implicits._
import cats.data._
import cats.free.Cofree
import scala.language.higherKinds

sealed trait Benefit

sealed trait StepResult
case class Checker(result: Boolean) extends StepResult
case class Calculator(result: Benefit) extends StepResult
case class Restricter(result: Benefit) extends StepResult

object KleisliTest {
  type CalcStep[A] = State[A, StepResult]

  type Detail[A] = Kleisli[CalcStep, A, A]
  case class Fix[F[_]](unfix: F[Fix[F]])
  case class BenefitDetailF[A](detail: List[Detail[A]], subDetail: List[A])
  type BenefitDetail = Fix[BenefitDetailF]

  val a: Detail[Benefit] =
    Kleisli { (benefit: Benefit) =>
      State.pure(Checker(true))
    }
}
