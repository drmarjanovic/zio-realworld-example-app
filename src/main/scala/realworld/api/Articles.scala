package realworld.api

import realworld.ArticlesRepo
import zio.http._
import zio.http.model.{Method, Status}
import zio.json._

object Articles {

  final val Routes: Http[ArticlesRepo, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      case req @ Method.GET -> BasePath / "articles" =>
        for {
          articles <- ArticlesRepo.fetchAll(req.limit, req.offset)
          data      = ArticlesResponse.fromDomain(articles).toJson
        } yield Response.json(data)

      case Method.GET -> BasePath / "articles" / slug =>
        ArticlesRepo.findBySlug(slug).map {
          case Some(article) => Response.json(ArticleResponse.fromDomain(article).toJson)
          case None          => unexpectedErrorFrom(s"Article $slug does not exist.")
        }

      case req @ Method.POST -> BasePath / "articles" =>
        for {
          spec    <- req.body.asString.map(_.fromJson[CreateArticleRequest])
          article  = spec.right.get.article
          created <- ArticlesRepo.insert(article.title, article.body, article.description)
          response = ArticleResponse.fromDomain(created).toJson
        } yield Response.json(response)

      case Method.DELETE -> BasePath / "articles" / slug =>
        ArticlesRepo.deleteBySlug(slug).as(Response.status(Status.NoContent))
    }
}
