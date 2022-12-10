package realworld.postgres

import io.getquill.jdbczio.Quill
import io.getquill.{PluralizedTableNames, PostgresZioJdbcContext, SnakeCase}
import org.flywaydb.core.Flyway
import realworld.config.DatabaseConfig
import zio.config.getConfig
import zio.{RIO, TaskLayer, ZIO}

import javax.sql.DataSource

object QuillContext extends PostgresZioJdbcContext(new SnakeCase with PluralizedTableNames) {

  def migrate: RIO[DatabaseConfig, Unit] =
    for {
      db     <- getConfig[DatabaseConfig]
      flyway <- ZIO.attempt(Flyway.configure().dataSource(db.url, db.user, db.password).load())
      _      <- ZIO.attempt(flyway.migrate())
    } yield ()

  lazy val live: TaskLayer[DataSource] = Quill.DataSource.fromPrefix("postgres")

}
