import sbt._

object Dependencies {

  private object Versions {
    val Flyway          = "9.8.1"
    val OrganizeImports = "0.6.0"
    val Scaluzzi        = "0.1.23"
    val Postgres        = "42.5.0"
    val Zio             = "2.0.3"
    val ZioConfig       = "3.0.2"
    val ZioHttp         = "0.0.3"
    val ZioJson         = "0.3.0"
    val ZioQuill        = "4.6.0"
  }

  lazy val All: List[ModuleID] =
    List(
      "org.flywaydb"   % "flyway-core"         % Versions.Flyway,
      "org.postgresql" % "postgresql"          % Versions.Postgres,
      "dev.zio"       %% "zio"                 % Versions.Zio,
      "dev.zio"       %% "zio-config"          % Versions.ZioConfig,
      "dev.zio"       %% "zio-config-magnolia" % Versions.ZioConfig,
      "dev.zio"       %% "zio-config-typesafe" % Versions.ZioConfig,
      "dev.zio"       %% "zio-json"            % Versions.ZioJson,
      "dev.zio"       %% "zio-http"            % Versions.ZioHttp,
      "io.getquill"   %% "quill-jdbc-zio"      % Versions.ZioQuill,
      "dev.zio"       %% "zio-test"            % Versions.Zio % Test,
      "dev.zio"       %% "zio-test-sbt"        % Versions.Zio % Test
    )

  lazy val ScalaFix: List[ModuleID] =
    List(
      "com.github.liancheng" %% "organize-imports" % Versions.OrganizeImports,
      "com.github.vovapolu"  %% "scaluzzi"         % Versions.Scaluzzi
    )

}
