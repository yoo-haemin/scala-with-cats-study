package chap04.catslib

import utest._
import HackingOnReaders._

object ReaderSpec extends TestSuite {
  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)


  val tests = Tests {
    * - { assert(checkLogin(1, "zerocool").run(db)) }

    * - { assert(!checkLogin(4, "davinci").run(db)) }


  }
}
