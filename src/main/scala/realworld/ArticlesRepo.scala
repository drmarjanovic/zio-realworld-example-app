package realworld

import realworld.postgres.PostgresArticlesRepo
import zio._

trait ArticlesRepo {
  def insert(title: String, body: String, description: String): Task[Article]

  def fetchAll(limit: Int, offset: Int): Task[List[Article]]

  def findBySlug(slug: String): Task[Option[Article]]

  def deleteBySlug(slug: String): Task[Long]
}

object ArticlesRepo {
  lazy val live: ULayer[PostgresArticlesRepo] = ZLayer.succeed(new PostgresArticlesRepo())
}
