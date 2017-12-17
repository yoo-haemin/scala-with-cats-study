package chap03.catslib

import cats.Contravariant
import cats.Show
import cats.instances.string._
import cats.syntax.contravariant._


object MyContravariant extends App { //Avoid name collision
  val showString = Show[String]
  
  val showSymbol = Contravariant[Show].
    contramap(showString)((sym: Symbol) => "'" + sym.name)
    
  println(showSymbol.show('dave))
}