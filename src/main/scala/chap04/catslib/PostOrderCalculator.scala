package chap04.catslib

import cats.data.{ State, NonEmptyList }
import cats.syntax.applicative._

object PostOrderCalculator {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] =
    State { xs =>
      sym match {
        case "+" =>
          val answer = xs.take(2).sum
          (answer :: xs.drop(2)) -> answer
        case "-" =>
          val operands = xs.take(2)
          val answer = operands(1) - operands(0)
          (answer :: xs.drop(2)) -> answer
        case "*" =>
          val answer = xs.take(2).product
          (answer :: xs.drop(2)) -> answer

        case "/" =>
          val operands = xs.take(2)
          val answer = operands(1) / operands(0)
          (answer :: xs.drop(2)) -> answer

        case _ =>
          val newInt = sym.toInt
          (newInt :: xs) -> newInt
      }
    }


  def evalAll(input: NonEmptyList[String]): CalcState[Int] =
    input.tail.foldLeft(evalOne(input.head)) { (acc: CalcState[Int], v: String) =>
      acc flatMap { _ => evalOne(v) }
    }
}
