import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    name         := "zio-realworld-example-app",
    description  := "Exemplary real world application built with Scala + ZIO.",
    organization := "com.github.drmarjanovic",
    developers := List(
      Developer(
        "drmarjanovic",
        "Dragutin Marjanovic",
        "dmarjanovic94@gmail.com",
        url("https://github.com/drmarjanovic")
      )
    ),
    scalaVersion      := "2.13.6",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalafixDependencies ++= ScalaFix,
    scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
    version                    := "0.0.1",
    Compile / scalacOptions    := List("-Wunused")
  )
)

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("prepare", "fix; fmt")
addCommandAlias("fix", "scalafixAll")
addCommandAlias("fixCheck", "scalafixAll --check")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
addCommandAlias("fmtCheck", "all scalafmtSbtCheck scalafmtCheckAll")

lazy val buildInfoSettings = List(
  buildInfoKeys    := List[BuildInfoKey](name, description, version),
  buildInfoPackage := "com.github.drmarjanovic.app"
)

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoSettings: _*)
  .settings(
    testFrameworks      := List(new TestFramework("zio.test.sbt.ZTestFramework")),
    libraryDependencies := All
  )
