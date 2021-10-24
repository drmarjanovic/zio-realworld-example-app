package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.api.routes.Application
import com.github.drmarjanovic.app.config.AppConfig
import zhttp.service.Server
import zio._

object Main extends App {

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    ZIO
      .serviceWith[AppConfig] { config =>
        val routes = Application.routes
        Server.start(config.http.port, routes)
      }
      .provideSomeLayer(AppConfig.live)
      .exitCode

}
