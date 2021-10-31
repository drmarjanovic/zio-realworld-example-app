package realworld.api

import realworld.ArticlesRepo
import zhttp.http._
import zio.Has
import zio.json._

object Articles {
  final val Routes: Http[Has[ArticlesRepo], Throwable, Request, UResponse] =
    Http.collectM[Request] {
      case req @ Method.GET -> Root / "articles" =>
        ArticlesRepo(_.fetchAll(req.limit, req.offset))
          .map(data => ArticlesResponse.fromDomain(data).toJson)
          .map(Response.jsonString)

      case _ @Method.GET -> Root / "articles" / slug =>
        ArticlesRepo(_.findBySlug(slug)).map {
          case Some(article) => Response.jsonString(ArticleResponse.fromDomain(article).toJson)
          case None          => unprocessableEntity(ErrorResponse.fromReasons(s"Article $slug does not exist.").toJson)
        }
    }
}
