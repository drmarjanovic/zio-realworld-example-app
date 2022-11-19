import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    description := "Exemplary real world application built with Scala + ZIO.",
    developers := List(
      Developer(
        "drmarjanovic",
        "Dragutin Marjanovic",
        "dmarjanovic94@gmail.com",
        url("https://github.com/drmarjanovic")
      ),
      Developer(
        "mijicd",
        "Dejan Mijic",
        "dmijic@acm.org",
        url("https://github.com/mijicd")
      )
    ),
    scalaVersion      := "2.13.10",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalafixDependencies ++= ScalaFix,
    scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
    version                    := "0.0.1",
    Compile / scalacOptions    := List("-Wunused")
  )
)

addCommandAlias("prepare", "fix; fmt")
addCommandAlias("check", "fixCheck; fmtCheck")
addCommandAlias("fix", "scalafixAll")
addCommandAlias("fixCheck", "scalafixAll --check")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
addCommandAlias("fmtCheck", "all scalafmtSbtCheck scalafmtCheckAll")

lazy val buildInfoSettings = List(
  buildInfoKeys    := List[BuildInfoKey](name, description, version),
  buildInfoPackage := "realworld"
)

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoSettings: _*)
  .settings(
    testFrameworks      := List(new TestFramework("zio.test.sbt.ZTestFramework")),
    libraryDependencies := All
  )
