package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.ArticlesRepo
import zhttp.http._
import zio.Has
import zio.json._

object Articles {
  val routes: Http[Has[ArticlesRepo], Throwable, Request, UResponse] =
    Http.collectM[Request] {
      case req @ Method.GET -> Root / "articles" =>
        val limit  = getQueryParam(req, "limit").getOrElse(20)
        val offset = getQueryParam(req, "offset").getOrElse(0)

        ArticlesRepo(_.fetchAll(limit, offset))
          .map(data => ArticlesResponse.fromDomain(data).toJson)
          .map(Response.jsonString)

      case _ @Method.GET -> Root / "articles" / slug =>
        ArticlesRepo(_.findBySlug(slug)).map {
          case Some(article) => Response.jsonString(ArticleResponse.fromDomain(article).toJson)
          case None          => unprocessableEntity(ErrorResponse.fromReasons(s"Article $slug does not exist.").toJson)
        }
    }
}
