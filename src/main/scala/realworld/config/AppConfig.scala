package realworld.config

import zio.config.{ReadError, _}
import zio.{IO, ZLayer}

import magnolia._
import typesafe._

final case class AppConfig(http: HttpConfig, db: DatabaseConfig)

object AppConfig {
  private val config: IO[ReadError[String], AppConfig] = read(
    descriptorForPureConfig[AppConfig] from TypesafeConfigSource.fromResourcePath
  )

  val live: ZLayer[Any, ReadError[String], AppConfig] = ZLayer.fromZIO(config)

}
