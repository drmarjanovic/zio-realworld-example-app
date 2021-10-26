import sbt._

object Dependencies {

  private object Zio {
    val Config = "1.0.6"
    val Http   = "1.0.0.0-RC17"
    val Json   = "0.1.5"
    val Magic  = "0.3.9"
    val Test   = "1.0.12"
  }

  private object Versions {
    val OrganizeImports = "0.5.0"
    val Scaluzzi        = "0.1.20"
  }

  lazy val All =
    List(
      "dev.zio"              %% "zio-config"          % Zio.Config,
      "dev.zio"              %% "zio-config-magnolia" % Zio.Config,
      "dev.zio"              %% "zio-config-typesafe" % Zio.Config,
      "io.d11"               %% "zhttp"               % Zio.Http,
      "dev.zio"              %% "zio-json"            % Zio.Json,
      "io.github.kitlangton" %% "zio-magic"           % Zio.Magic,
      "dev.zio"              %% "zio-test"            % Zio.Test % Test,
      "dev.zio"              %% "zio-test-sbt"        % Zio.Test % Test
    )

  lazy val ScalaFix =
    List(
      "com.github.liancheng" %% "organize-imports" % Versions.OrganizeImports,
      "com.github.vovapolu"  %% "scaluzzi"         % Versions.Scaluzzi
    )

}
