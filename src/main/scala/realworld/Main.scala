package realworld

import realworld.api.Articles
import realworld.config.AppConfig
import realworld.postgres.QuillContext
import zio._
import zio.http.{Server, ServerConfig}

object Main extends ZIOAppDefault {

  private[this] def runServer =
    (for {
      _     <- ZIO.service[AppConfig]
      routes = Articles.Routes
      _     <- Server.serve(routes)
    } yield ()).provide(AppConfig.live, ArticlesRepo.live, Server.live, ServerConfig.live)

  override def run: ZIO[Any, Throwable, ExitCode] =
    (for {
      _    <- QuillContext.migrate
      code <- runServer.exitCode
    } yield code).provide(AppConfig.live)

}
