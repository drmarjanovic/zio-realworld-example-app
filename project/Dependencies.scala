import sbt._

object Dependencies {

  private object Versions {
    val Flyway          = "8.0.2"
    val OrganizeImports = "0.5.0"
    val Postgres        = "42.2.24"
    val Quill           = "3.10.0"
    val Scaluzzi        = "0.1.20"
    val ZioConfig       = "1.0.6"
    val ZioHttp         = "1.0.0.0-RC17"
    val ZioJson         = "0.1.5"
    val ZioMagic        = "0.3.9"
    val ZioTest         = "1.0.12"
  }

  lazy val All =
    List(
      "dev.zio"              %% "zio-config"          % Versions.ZioConfig,
      "dev.zio"              %% "zio-config-magnolia" % Versions.ZioConfig,
      "dev.zio"              %% "zio-config-typesafe" % Versions.ZioConfig,
      "dev.zio"              %% "zio-json"            % Versions.ZioJson,
      "io.d11"               %% "zhttp"               % Versions.ZioHttp,
      "io.getquill"          %% "quill-jdbc-zio"      % Versions.Quill,
      "io.github.kitlangton" %% "zio-magic"           % Versions.ZioMagic,
      "org.flywaydb"          % "flyway-core"         % Versions.Flyway,
      "org.postgresql"        % "postgresql"          % Versions.Postgres,
      "dev.zio"              %% "zio-test"            % Versions.ZioTest % Test,
      "dev.zio"              %% "zio-test-sbt"        % Versions.ZioTest % Test
    )

  lazy val ScalaFix =
    List(
      "com.github.liancheng" %% "organize-imports" % Versions.OrganizeImports,
      "com.github.vovapolu"  %% "scaluzzi"         % Versions.Scaluzzi
    )

}
