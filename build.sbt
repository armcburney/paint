import sbtassembly.Plugin.AssemblyKeys._

name          := "paint"

version       := "0.1"

scalaVersion  := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "org.specs2"                 % "specs2-core_2.11" % "3.8.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.6"
)

assemblySettings
