package realworld

import zio._

trait ArticlesRepo {
  def insert(title: String, body: String, description: String): Task[Article]

  def fetchAll(limit: Int, offset: Int): Task[List[Article]]

  def findBySlug(slug: String): Task[Option[Article]]

  def deleteBySlug(slug: String): Task[Long]
}

object ArticlesRepo {
  def insert(title: String, body: String, description: String): RIO[ArticlesRepo, Article] =
    ZIO.serviceWithZIO(_.insert(title, body, description))

  def fetchAll(limit: Int, offset: Int): RIO[ArticlesRepo, List[Article]] =
    ZIO.serviceWithZIO(_.fetchAll(limit, offset))

  def findBySlug(slug: String): RIO[ArticlesRepo, Option[Article]] =
    ZIO.serviceWithZIO(_.findBySlug(slug))

  def deleteBySlug(slug: String): RIO[ArticlesRepo, Long] =
    ZIO.serviceWithZIO(_.deleteBySlug(slug))
}
