package com.yoohaemin

//Solution to maximum subarray problem by Richard Bird
//http://comjnl.oxfordjournals.org/content/32/2/122.full.pdf
object MaximumSubarray {
  def apply(xs: Seq[Int]): Long = {
    xs.foldLeft(0L -> 0L) { case ((u, v), x) =>
      val w = (v + x) max 0
      (u max w) -> w
    }._1
  }
}
