package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.api.routes.{Application, Articles}
import com.github.drmarjanovic.app.config.AppConfig
import com.github.drmarjanovic.app.repo.ArticlesRepo
import zhttp.service.Server
import zio._
import zio.magic._

object Main extends App {

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    ZIO
      .serviceWith[AppConfig] { config =>
        runServer(config.http.port)
      }
      .inject(AppConfig.live)
      .exitCode

  private[this] def runServer(port: Int) = {
    val routes = Application.routes +++ Articles.routes
    Server.start(port, routes).inject(ArticlesRepo.test)
  }

}
