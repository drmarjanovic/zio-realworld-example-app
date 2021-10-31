package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.ArticlesRepo
import zhttp.http._
import zio.Has
import zio.json._

object Articles {
  final val Routes: Http[Has[ArticlesRepo], Throwable, Request, UResponse] =
    Http.collectM[Request] {
      case req @ Method.GET -> Root / "articles" =>
        ArticlesRepo(_.fetchAll(req.limit.getOrElse(20), req.offset.getOrElse(0)))
          .map(data => ArticlesResponse.fromDomain(data).toJson)
          .map(Response.jsonString)

      case _ @Method.GET -> Root / "articles" / slug =>
        ArticlesRepo(_.findBySlug(slug)).map {
          case Some(article) => Response.jsonString(ArticleResponse.fromDomain(article).toJson)
          case None          => unprocessableEntity(ErrorResponse.fromReasons(s"Article $slug does not exist.").toJson)
        }
    }
}
