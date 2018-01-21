package chap08

import utest._
import scala.concurrent.Future

object UptimeTest extends TestSuite {
  val tests = ???
  // class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient {
  //   def getUptime(hostname: String): Future[Int] =
  //     Future.successful(hosts.getOrElse(hostname, 0))
  // }

  // val tests = Tests {
  //   'wrong - {
  //     val hosts = Map("host1" -> 10, "host2" -> 6)
  //     val client = new TestUptimeClient(hosts)
  //     val service  = new UptimeService(client)
  //     val actual   = service.getTotalUptime(hosts.keys.toList)
  //     val expected = hosts.values.sum
  //     assert(actual != expected)
  //   }

  //   'a - {

  //   }
  // }
}
