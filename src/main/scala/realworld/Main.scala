package realworld

import realworld.api.{Application, Articles}
import realworld.config.{AppConfig, HttpConfig}
import realworld.postgres.QuillContext
import zhttp.service.Server
import zio._
import zio.magic._

object Main extends App {

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    (QuillContext.migrate *> runServer)
      .inject(AppConfig.live)
      .exitCode

  private[this] def runServer =
    ZIO.serviceWith[HttpConfig] { config =>
      val routes = Application.Routes +++ Articles.Routes
      Server
        .start(config.port, routes)
        .inject(
          QuillContext.live,
          ArticlesRepo.live
        )
    }

}
