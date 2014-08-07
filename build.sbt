organization := "sgeorgi"

name := "socket_sqrt"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.1.1" % "test"
)