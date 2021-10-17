package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.api.routes.Articles
import zio._
import zhttp.service.Server

object HttpServer extends App {

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val routes = Articles.routes
    Server.start(9000, routes).exitCode
  }

}
