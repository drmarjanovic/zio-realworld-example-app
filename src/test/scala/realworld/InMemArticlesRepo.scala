package realworld
import zio._

final class InMemArticlesRepo extends ArticlesRepo {

  private var articles = List.empty[Article]

  def insert(title: String, body: String, description: String): Task[Article] =
    for {
      id  <- Random.nextIntBounded(1000)
      now <- Clock.localDateTime
      article = Article(
                  id = ArticleId(id),
                  title = title,
                  slug = title,
                  body = body,
                  description = description,
                  createdAt = now,
                  updatedAt = now
                )
    } yield {
      articles = articles :+ article
      article
    }

  def fetchAll(limit: Int, offset: Int): Task[List[Article]] =
    ZIO.succeed(articles.slice(offset, offset + limit))

  def findBySlug(slug: String): Task[Option[Article]] =
    ZIO.succeed(articles.find(_.slug == slug))

  def deleteBySlug(slug: String): Task[Long] =
    ZIO.succeed {
      val exists = articles.find(_.slug == slug)
      articles = articles.filterNot(_.slug == slug)
      exists.fold(0)(_ => 1)
    }
}

object InMemArticlesRepo {
  lazy val live: ULayer[InMemArticlesRepo] = ZLayer.succeed(new InMemArticlesRepo())
}
