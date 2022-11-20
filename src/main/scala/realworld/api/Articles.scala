package realworld.api

import realworld.ArticlesRepo
import zio.ZIO
import zio.http._
import zio.http.model.{Method, Status}
import zio.json._

object Articles {

  final val Routes: Http[ArticlesRepo, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      case req @ Method.GET -> BasePath / "articles" =>
        for {
          repo     <- ZIO.service[ArticlesRepo]
          articles <- repo.fetchAll(req.limit, req.offset)
          data      = ArticlesResponse.fromDomain(articles).toJson
        } yield Response.json(data)

      case Method.GET -> BasePath / "articles" / slug =>
        for {
          repo    <- ZIO.service[ArticlesRepo]
          article <- repo.findBySlug(slug)
        } yield article match {
          case Some(a) => Response.json(ArticleResponse.fromDomain(a).toJson)
          case None    => unexpectedErrorFrom(s"Article $slug does not exist.")
        }

      case req @ Method.POST -> BasePath / "articles" =>
        for {
          repo    <- ZIO.service[ArticlesRepo]
          spec    <- req.body.asString.map(_.fromJson[CreateArticleRequest])
          article  = spec.right.get.article
          created <- repo.insert(article.title, article.body, article.description)
          response = ArticleResponse.fromDomain(created).toJson
        } yield Response.json(response)

      case Method.DELETE -> BasePath / "articles" / slug =>
        for {
          repo <- ZIO.service[ArticlesRepo]
          _    <- repo.deleteBySlug(slug)
        } yield Response.status(Status.NoContent)
    }
}
