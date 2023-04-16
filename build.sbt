ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "TIP"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-featurespec" % "3.2.15" % "test"

libraryDependencies += "org.typelevel" %% "paiges-core" % "0.4.2"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0"
