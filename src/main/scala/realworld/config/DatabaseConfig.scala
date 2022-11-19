package realworld.config

final case class DatabaseConfig(host: String, port: Int, name: String, user: String, password: String) {
  lazy val url: String = s"jdbc:postgresql://$host:$port/$name"
}

object DatabaseConfig {
//  private val config: IO[ReadError[String], DatabaseConfig] = read(
//    descriptorForPureConfig[DatabaseConfig] from TypesafeConfigSource.fromResourcePath
//  )
//
//  val live: ZLayer[Any,ReadError[String],DatabaseConfig] = ZLayer.fromZIO(config)
}
