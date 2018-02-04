package chap03

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A

  private[this] val aenc = encode _
  private[this] val adec = decode _

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    override def encode(b: B): String =
      aenc(enc(b))

    override def decode(bstr: String): B =
      dec(adec(bstr))
  }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)
  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)
    
  implicit val stringCodec = new Codec[String] {
    def encode(value: String): String = value
    def decode(value: String): String = value
  }
  
  implicit val doubleCodec = stringCodec.imap(
    dec = { (s: String) => s.toDouble }, 
    enc = { (d: Double) => d.toString }
  )
}
