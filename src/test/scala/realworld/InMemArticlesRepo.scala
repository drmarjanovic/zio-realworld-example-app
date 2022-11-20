package realworld
import zio.{Task, ULayer, ZIO, ZLayer}

import java.time.LocalDateTime
import scala.collection.mutable

final class InMemArticlesRepo private (init: mutable.Seq[Article] = mutable.Seq.empty[Article])
    extends ArticlesRepo {

  private var articles = init

  override def insert(title: String, body: String, description: String): Task[Article] =
    ZIO.succeed {
      val article = Article(
        id = ArticleId(1),
        title = title,
        slug = title,
        body = body,
        description = description,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
      )

      articles = articles :+ article
      article
    }

  override def fetchAll(limit: Int, offset: Int): Task[List[Article]] =
    ZIO.succeed(articles.slice(offset, offset + limit).toList)

  override def findBySlug(slug: String): Task[Option[Article]] =
    ZIO.succeed(articles.find(_.slug == slug))

  override def deleteBySlug(slug: String): Task[Long] =
    ZIO.succeed {
      val exists = articles.find(_.slug == slug)
      articles = articles.filterNot(_.slug == slug)
      exists.fold(0)(_ => 1)
    }
}

object InMemArticlesRepo {
  lazy val live: ULayer[InMemArticlesRepo] = ZLayer.succeed(new InMemArticlesRepo())
}
