package com.github.drmarjanovic.app.pg

import com.github.drmarjanovic.app.config.DatabaseConfig
import io.getquill.context.ZioJdbc.QDataSource
import io.getquill.{PluralizedTableNames, PostgresZioJdbcContext, SnakeCase}
import org.flywaydb.core.Flyway
import zio.{Has, RIO, Task, TaskLayer, UIO, ZIO}

import java.io.Closeable
import javax.sql.DataSource

object QuillContext extends PostgresZioJdbcContext(new SnakeCase with PluralizedTableNames) {

  lazy val live: TaskLayer[Has[DataSource with Closeable]] = QDataSource.fromPrefix("postgres")

  def migrate: RIO[Has[DatabaseConfig], Any] =
    ZIO.serviceWith[DatabaseConfig] { config =>
      for {
        flyway <- UIO(Flyway.configure().dataSource(config.url, config.user, config.password).load())
        _      <- Task(flyway.migrate())
      } yield ()
    }

}
