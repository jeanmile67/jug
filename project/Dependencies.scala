import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val cats = "org.typelevel" %% "cats-core" % "1.5.0"
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % "1.0.0" withSources() withJavadoc()

}
