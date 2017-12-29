package chap04

import cats.data.State
import cats.syntax.applicative._

object PostOrderCalc {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => State { case h1 :: h2 :: _ => (List(h1 + h2), h1 + h2) }
    case "-" => State { case h1 :: h2 :: _ => (List(h2 - h1), h2 - h1) }
    case "*" => State { case h1 :: h2 :: _ => (List(h1 * h2), h1 * h2) }
    case "/" => State { case h1 :: h2 :: _ => (List(h2 / h1), h2 / h1) }
    case s => State { l => (l :+ s.toInt, s.toInt) }
  }

//  @annotation.tailrec
  def evalAll2(input: List[String]): CalcState[Int] = input match {
    case h :: Nil => evalOne(h)
    case h :: t => evalOne(h) flatMap( _ => evalAll(t))
  }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState])((a, b) => {
      a flatMap { _ => evalOne(b) }
    })

  def evalInput(input: String): Int = evalAll(input.split(" ").toList).runA(Nil).value
}
