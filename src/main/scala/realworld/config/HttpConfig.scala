package realworld.config

final case class HttpConfig(port: Int)

object HttpConfig {
//  private val config: IO[ReadError[String], HttpConfig] = read(
//    descriptorForPureConfig[HttpConfig] from TypesafeConfigSource.fromResourcePath
//  )
//
//  val live: ZLayer[Any,ReadError[String],HttpConfig] = ZLayer.fromZIO(config)
}
