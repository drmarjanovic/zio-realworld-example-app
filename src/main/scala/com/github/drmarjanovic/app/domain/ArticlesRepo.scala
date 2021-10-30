package com.github.drmarjanovic.app.domain

import com.github.drmarjanovic.app.pg.PgArticlesRepo
import zio._

import java.io.Closeable
import javax.sql.DataSource

trait ArticlesRepo {
  def all: Task[List[Article]]
}

object ArticlesRepo extends Accessible[ArticlesRepo] {
  lazy val live: URLayer[Has[DataSource with Closeable], Has[ArticlesRepo]] = PgArticlesRepo.toLayer
}