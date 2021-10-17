import Dependencies._

lazy val info = List(
  name := "zio-realworld-example-app",
  description := "Exemplary real world application built with Scala + ZIO.",
  organization := "com.github.drmarjanovic",
  developers := List(
    Developer(
      "drmarjanovic",
      "Dragutin Marjanovic",
      "dmarjanovic94@gmail.com",
      url("https://github.com/drmarjanovic")
    )
  ),
  version := "0.0.1"
)

lazy val root = (project in file("."))
  .settings(info: _*)
  .settings {
    scalaVersion := "2.13.6"
    libraryDependencies ++= {
      val zio   = List(zioHttp, zioJson)
      val utils = List(jodaTime)

      zio ++ utils
    }
  }
