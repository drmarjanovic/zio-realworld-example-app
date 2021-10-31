package realworld.postgres

import io.getquill.context.ZioJdbc._
import realworld.{Article, ArticlesRepo}
import zio.{Has, IO, Task}

import java.io.Closeable
import java.sql.SQLException
import java.util.UUID
import javax.sql.DataSource

final case class PostgresArticlesRepo(pool: DataSource with Closeable) extends ArticlesRepo {

  import QuillContext._

  private val env = Has(pool)

  def insert(title: String, body: String, description: String): IO[SQLException, Article] =
    run(
      articles
        .insert(
          _.title       -> lift(title),
          _.slug        -> lift(UUID.randomUUID.toString),
          _.body        -> lift(body),
          _.description -> lift(description)
        )
        .returning(r => r)
    ).onDS.provide(env)

  def fetchAll(limit: Int, offset: Int): Task[List[Article]] =
    run(articles.drop(lift(offset)).take(lift(limit))).onDS.provide(env)

  def findBySlug(slug: String): Task[Option[Article]] =
    run(articles.filter(_.slug == lift(slug))).map(_.headOption).onDS.provide(env)

}
