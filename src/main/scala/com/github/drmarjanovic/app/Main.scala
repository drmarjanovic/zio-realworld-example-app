package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.api.{Application, Articles}
import com.github.drmarjanovic.app.config.{AppConfig, HttpConfig}
import com.github.drmarjanovic.app.pg.QuillContext
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
      val routes = Application.routes +++ Articles.routes
      Server
        .start(config.port, routes)
        .inject(
          QuillContext.live,
          ArticlesRepo.live
        )
    }

}
