name := "cats-sandbox"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.3"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",   // source files are in UTF-8
  "-deprecation",         // warn about use of deprecated APIs
  "-unchecked",           // warn about unchecked type parameters
  "-feature",             // warn about misused language features
  "-language:higherKinds",// allow higher kinded types without `import scala.language.higherKinds`
  "-Xlint",               // enable handy linter warnings
  "-Ypartial-unification",// allow the compiler to unify type constructors of different arities
  "-Ywarn-unused-import",
  "-Ywarn-unused"
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0",
  "com.lihaoyi" %% "utest" % "0.6.0" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")


addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")
