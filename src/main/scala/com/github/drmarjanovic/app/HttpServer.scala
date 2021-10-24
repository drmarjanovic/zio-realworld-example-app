package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.api.routes.Articles
import com.github.drmarjanovic.app.config.AppConfig
import zhttp.service.Server
import zio._
import zio.config.getConfig
import zio.config.magnolia.DeriveConfigDescriptor.descriptor
import zio.config.typesafe.TypesafeConfig

object HttpServer extends App {

  private val configLayer = TypesafeConfig.fromDefaultLoader(descriptor[AppConfig])

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    val server = getConfig[AppConfig].flatMap { config =>
      val routes = Articles.routes
      Server.start(config.http.port, routes)
    }.provideCustomLayer(configLayer)

    server.exitCode
  }

}
