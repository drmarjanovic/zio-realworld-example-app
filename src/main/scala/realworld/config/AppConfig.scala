package realworld.config

import zio.Layer
import zio.config.ReadError
import zio.config.magnolia._
import zio.config.syntax._
import zio.config.typesafe._

final case class AppConfig(http: HttpConfig, db: DatabaseConfig)

object AppConfig {

  lazy val live: Layer[ReadError[String], AppConfig with DatabaseConfig with HttpConfig] = {
    val config = TypesafeConfig.fromResourcePath(descriptor[AppConfig])
    config >+> config.narrow(_.db) >+> config.narrow(_.http)
  }

}
