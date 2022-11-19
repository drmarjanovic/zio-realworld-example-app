package realworld.postgres

import io.getquill.jdbczio.Quill
import io.getquill.{PluralizedTableNames, PostgresZioJdbcContext, SnakeCase}
import org.flywaydb.core.Flyway
import realworld.config.AppConfig
import zio.{RIO, ZIO, ZLayer}

import javax.sql.DataSource

object QuillContext extends PostgresZioJdbcContext(new SnakeCase with PluralizedTableNames) {

  def migrate: RIO[AppConfig, Unit] =
    for {
      conf   <- ZIO.service[AppConfig]
      db      = conf.db
      flyway <- ZIO.attempt(Flyway.configure().dataSource(db.url, db.user, db.password).load())
      _      <- ZIO.attempt(flyway.migrate())
    } yield ()

  lazy val live: ZLayer[Any, Throwable, DataSource] = Quill.DataSource.fromPrefix("postgres")

}
