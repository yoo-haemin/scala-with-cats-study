package chap04

import utest._
import LoginSystem._

object LoginSystemSpec extends TestSuite {
  val tests = Tests{
    "It should login" - {
      val users = Map(
        1 -> "Zedd",
        2 -> "Kygo",
        3 -> "Mago"
      )

      val passwords = Map(
        "Zedd" -> "spectrum",
        "Kygo" -> "stargrazing",
        "Mago" -> "outlines"
      )

      val db = Db(users, passwords)

      assert(checkLogin(1, "spectrum").run(db))
    }
  }
}