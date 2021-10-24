package com.github.drmarjanovic.app.config

import zio.config.ReadError
import zio.config.magnolia.DeriveConfigDescriptor
import zio.config.syntax._
import zio.config.typesafe.TypesafeConfig
import zio.{Has, Layer}

final case class AppConfig(http: HttpConfig, db: DatabaseConfig)

object AppConfig {
  private[this] final val Descriptor = DeriveConfigDescriptor.descriptor[AppConfig]

  lazy val live: Layer[ReadError[String], Has[AppConfig] with Has[HttpConfig] with Has[DatabaseConfig]] = {
    val appConfig = TypesafeConfig.fromDefaultLoader(Descriptor)

    appConfig >+> appConfig.narrow(_.http) >+> appConfig.narrow(_.db)
  }
}
