package realworld

import realworld.api.{Application, Articles}
import realworld.config.{AppConfig, HttpConfig}
import realworld.postgres.QuillContext
import zio._
import zio.http.{Server, ServerConfig}

object Main extends ZIOAppDefault {

  private[this] def runServer: Task[Unit] = {
    val serverConfigLive =
      ZLayer.fromZIO {
        for {
          http <- ZIO.service[HttpConfig]
        } yield ServerConfig.default.port(http.port)
      }

    (for {
      _     <- ZIO.service[HttpConfig]
      routes = Application.Routes ++ Articles.Routes
      _     <- Server.serve(routes)
    } yield ()).provide(AppConfig.live, ArticlesRepo.live, Server.live, serverConfigLive)
  }

  override def run: ZIO[Any, Throwable, ExitCode] =
    (for {
      _    <- QuillContext.migrate
      code <- runServer.exitCode
    } yield code).provide(AppConfig.live)

}
