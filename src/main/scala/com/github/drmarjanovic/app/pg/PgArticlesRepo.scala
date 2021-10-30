package com.github.drmarjanovic.app.pg

import com.github.drmarjanovic.app.domain.{Article, ArticlesRepo}
import io.getquill.context.ZioJdbc._
import zio.{Has, Task}

import java.io.Closeable
import javax.sql.DataSource

final case class PgArticlesRepo(pool: DataSource with Closeable) extends ArticlesRepo {

  import QuillContext._

  private val env = Has(pool)

  override def all(limit: Int, offset: Int): Task[List[Article]] =
    run(articles.drop(lift(offset)).take(lift(limit))).onDS.provide(env)

}
