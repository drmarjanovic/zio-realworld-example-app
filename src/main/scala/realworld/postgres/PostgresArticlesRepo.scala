package realworld.postgres

import realworld.{Article, ArticlesRepo}
import zio.Task

import java.util.UUID

final class PostgresArticlesRepo extends ArticlesRepo {
  import QuillContext._

  private val env = QuillContext.live

  def insert(title: String, body: String, description: String): Task[Article] =
    run(
      articles
        .insert(
          _.title       -> lift(title),
          _.slug        -> lift(UUID.randomUUID.toString),
          _.body        -> lift(body),
          _.description -> lift(description)
        )
        .returning(r => r)
    ).provide(env)

  def fetchAll(limit: Int, offset: Int): Task[List[Article]] =
    run(articles.drop(lift(offset)).take(lift(limit))).provide(env)

  def findBySlug(slug: String): Task[Option[Article]] =
    run(articles.filter(_.slug == lift(slug))).map(_.headOption).provide(env)

  def deleteBySlug(slug: String): Task[Long] =
    run(articles.filter(_.slug == lift(slug)).delete).map(_.self).provide(env)

}
