package chap01

trait Yongjin[A] {
  def coding(code: A): String
}

object YongjinInstaces {
  implicit val machineLeaning: Yongjin[Int] = new Yongjin[Int] {
    def coding(code: Int): String = code.toString + " error"
  }

  implicit val javascript: Yongjin[String] = new Yongjin[String] {
    def coding(code: String): String = code + ".js"
  }
}

object Yongjin {
  def coding[A](code: A)(implicit p: Yongjin[A]): String = p.coding(code)
}
