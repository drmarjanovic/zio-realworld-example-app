import sbt._

object Dependencies {

  private object Zio {
    val Http = "1.0.0.0-RC17"
    val Json = "0.1.5"
  }

  private object Versions {
    val JodaTime = "2.10.12"
  }

  val zioHttp: ModuleID = "io.d11"  %% "zhttp"    % Zio.Http
  val zioJson: ModuleID = "dev.zio" %% "zio-json" % Zio.Json

  val jodaTime: ModuleID = "joda-time" % "joda-time" % Versions.JodaTime

}
