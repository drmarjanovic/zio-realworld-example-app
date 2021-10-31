package realworld.config

final case class DatabaseConfig(host: String, port: Int, name: String, user: String, password: String) {
  lazy val url: String = s"jdbc:postgresql://$host:$port/$name"
}
