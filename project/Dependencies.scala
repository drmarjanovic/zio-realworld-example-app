import sbt._

object Dependencies {

  private object ZioVersions {
    val Config = "1.0.6"
    val Http   = "1.0.0.0-RC17"
    val Json   = "0.1.5"
    val Magic  = "0.3.9"
    val Test   = "1.0.12"
  }

  private object Versions {
    val Flyway          = "8.0.2"
    val OrganizeImports = "0.5.0"
    val Postgres        = "42.2.24"
    val Quill           = "3.10.0"
    val Scaluzzi        = "0.1.20"
  }

  lazy val Zio =
    List(
      "dev.zio"              %% "zio-config"          % ZioVersions.Config,
      "dev.zio"              %% "zio-config-magnolia" % ZioVersions.Config,
      "dev.zio"              %% "zio-config-typesafe" % ZioVersions.Config,
      "io.d11"               %% "zhttp"               % ZioVersions.Http,
      "dev.zio"              %% "zio-json"            % ZioVersions.Json,
      "io.github.kitlangton" %% "zio-magic"           % ZioVersions.Magic,
      "dev.zio"              %% "zio-test"            % ZioVersions.Test % Test,
      "dev.zio"              %% "zio-test-sbt"        % ZioVersions.Test % Test
    )

  lazy val Core =
    List(
      "org.flywaydb"   % "flyway-core"    % Versions.Flyway,
      "org.postgresql" % "postgresql"     % Versions.Postgres,
      "io.getquill"   %% "quill-jdbc-zio" % Versions.Quill
    )

  lazy val ScalaFix =
    List(
      "com.github.liancheng" %% "organize-imports" % Versions.OrganizeImports,
      "com.github.vovapolu"  %% "scaluzzi"         % Versions.Scaluzzi
    )

}
