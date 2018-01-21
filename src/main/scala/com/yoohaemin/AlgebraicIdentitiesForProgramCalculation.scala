package com.yoohaemin

/*
 * Trying to write down what's in this paper: http://comjnl.oxfordjournals.org/content/32/2/122.full.pdf
 */
object AlgebraicIdentitiesForProgramCalculation {

  //2. Map and Fold
  def map[A, B](f: A => B)(xs: List[A]): List[B] = xs match {
    case Nil => Nil
    case h :: t => f(h) :: map(f)(t)
  }

  //Alternative recursive characterisation
  def mapAlt[A, B](f: A => B)(xs: List[A]): List[B] = xs match {
    case Nil => Nil

    // I can't think of a way to encode the following (Haskell?) code from the paper
    // map f (xs ++ [x]) = (map f xs) ++ [f x]
    case _ => ???
  }

  // Probably the most useful law about map (according to the paper)
  // Returns true for all valid lists
  def mapDistributivity[A,B,C](f: A => B, g: B => C)(xs: List[A]): Boolean = {
    val encoding1 = map(f) _ andThen map(g)
    val encoding2 = map(f andThen g) _

    encoding1(xs) == encoding2(xs)
  }

  def foldr[A, B](f: (A, B) => B)(z: B)(xs: List[A]): B = xs match {
    //I could have written f as the last parameter, that will eliminate the need for type annotation when calling this function
    case Nil => z
    case h :: t => f(h, foldr(f)(z)(t))
  }

  @annotation.tailrec
  def foldl[A, B](f: (B, A) => B)(z: B)(xs: List[A]): B = xs match {
    //Ditto here
    case Nil => z
    case h :: t => foldl(f)(f(z, h))(t)
  }

  //It seems that I can't write in point-free style with generics
  def concat[A](xs: List[List[A]]) = //flatten?
    foldr((xs: List[A], ys: List[A]) => xs ++ ys)(List.empty[A])(xs)

  //Likely to overflow... it's scala. There's no Integer, use BigInt
  def sum(xs: List[Int]) =
    foldl((i: Int, j: Int) => i + j)(0)(xs)

  //Will most likely overflow... it's scala
  def product(xs: List[Int]) =
    foldl((i: Int, j: Int) => i * j)(1)(xs)

  def min(xs: List[Int]) =
    foldl((acc: Int, v: Int) => if (acc < v) acc else v)(Int.MaxValue)(xs)

  def max(xs: List[Int]) =
    foldl((acc: Int, v: Int) => if (acc > v) acc else v)(Int.MinValue)(xs)

  // (7) Returns true for all valid lists
  def mapPromotion[A,B](f: A => B)(xs: List[List[A]]): Boolean = {

    //the result of concatenating a list of lists and then applying f to each element
    val encoding1: (List[List[A]]) => List[B] =
      concat[A]_ andThen map(f)

    //applying (map f) to each component list and then concatenating the outcomes
    val encoding2: (List[List[A]]) => List[B] =
      map(map(f))_ andThen concat

    //are the same
    encoding1(xs) == encoding2(xs)
  }

  // (8) Returns true for all valid lists, if f is an associative operator and a is an identity element
  def foldPromotion[A](f: (A, A) => A, a: A)(xs: List[List[A]]): Boolean = {
    val encoding1: (List[List[A]]) => A =
      foldl(f)(a)_ compose concat

    val encoding2: (List[List[A]]) => A =
      foldl(f)(a)_ compose map(foldl(f)(a))

    //are the same
    encoding1(xs) == encoding2(xs)
  }

  //For example,
  def sumWithFoldPromotion(xs: List[List[Int]]) = {
    val encoding1: (List[List[Int]]) => Int =
      sum _ compose concat

    val encoding2: (List[List[Int]]) => Int =
      sum _ compose map(sum)

    encoding1(xs) == encoding2(xs)
  }

  //The fold promotion law requires f to be associative with identity element a. If we forgo this condition, we get another law:
  def anotherLaw[A](f: (A, A) => A, a: A)(xs: List[List[A]]): Boolean = {

    val encoding1: List[List[A]] => A =
      foldl(f)(a)_ compose concat

    val encoding2: List[List[A]] => A = {
      val g = (u: A, x: List[A]) => foldl(f)(u)(x)
      foldl(g)(a)
    }

    encoding1(xs) == encoding2(xs)
  }

  //Later...
  def foldMapFusion[A](f: (A, A) => A, a: A)(xs: List[A]): Boolean = {
    ???
  }

  object MiniMax {

  }

  //@annotation.tailrec
  //def scanl[A](f: (A, A) => A)(a: A)(xs: Seq[A]): Seq[A] = xs match {
  //  case xs if xs.isEmpty =>
  //    Seq(a)
  //  case xs =>
  //    Seq(a) ++ scanl(f)(f(a, xs.head))(xs.tail)
  //}


  //def inits[A](as: Seq[A]) = {
  //  val f = (as: Seq[A], a: A) => as ++ Seq(a)
  //  scanl(f)(List.empty[A])
  //}

  //def tails[A](xs: Seq[A]) = xs.tails
  def segs[A](xs: Seq[A]) = (0 to xs.length) flatMap { xs combinations _ }

  //def mss1 = max _ compose map(sum)_ compose segs

}
