import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    name := "zio-realworld-example-app",
    description := "Exemplary real world application built with Scala + ZIO.",
    organization := "com.github.drmarjanovic",
    scalaVersion := "2.13.6",
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
)

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
addCommandAlias("fmtCheck", "all scalafmtSbtCheck scalafmtCheckAll")

lazy val root = (project in file(".")).settings(
  testFrameworks := List(new TestFramework("zio.test.sbt.ZTestFramework")),
  libraryDependencies ++= {
    val zio = List(zioConfig, zioConfigMagnolia, zioConfigTypesafe, zioHttp, zioJson)

    val tests = List(zioTest, zioTestSbt).map(_ % Test)

    zio ++ tests
  }
)
