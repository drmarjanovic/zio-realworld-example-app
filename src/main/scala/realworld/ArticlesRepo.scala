package realworld

import realworld.postgres.PostgresArticlesRepo
import zio._

import java.io.Closeable
import java.sql.SQLException
import javax.sql.DataSource

trait ArticlesRepo {
  def insert(title: String, body: String, description: String): IO[SQLException, Article]

  def fetchAll(limit: Int, offset: Int): Task[List[Article]]

  def findBySlug(slug: String): Task[Option[Article]]
}

object ArticlesRepo extends Accessible[ArticlesRepo] {
  lazy val live: URLayer[Has[DataSource with Closeable], Has[ArticlesRepo]] = PostgresArticlesRepo.toLayer
}
