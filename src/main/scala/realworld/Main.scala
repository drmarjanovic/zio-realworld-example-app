package realworld

import realworld.api.{Application, Articles}
import realworld.config.{AppConfig, HttpConfig}
import realworld.postgres.{PostgresArticlesRepo, QuillContext}
import zio._
import zio.config.getConfig
import zio.http.{Server, ServerConfig}

object Main extends ZIOAppDefault {

  override def run: Task[Unit] = (QuillContext.migrate *> runServer).provide(AppConfig.live)

  private[this] def runServer: Task[Unit] = {
    val serverConfigLive =
      ZLayer.fromZIO {
        for {
          http <- getConfig[HttpConfig]
        } yield ServerConfig.default.port(http.port)
      }

    (for {
      _     <- getConfig[HttpConfig]
      routes = Application.Routes ++ Articles.Routes
      _     <- Server.serve(routes)
    } yield ()).provide(AppConfig.live, PostgresArticlesRepo.live, Server.live, serverConfigLive)
  }

}
