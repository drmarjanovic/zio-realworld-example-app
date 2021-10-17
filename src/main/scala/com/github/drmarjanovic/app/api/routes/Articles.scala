package com.github.drmarjanovic.app.api.routes

import com.github.drmarjanovic.app.MockData
import com.github.drmarjanovic.app.api.model.{ ArticleResponse, ArticlesResponse }
import zio.json._
import zhttp.http._

object Articles {

  val routes: Http[Any, Nothing, Request, UResponse] =
    Http.collect[Request] {
      case _ @ Method.GET -> Root / "articles" =>
        val response = ArticlesResponse.fromDomain(MockData.articles)
        Response.jsonString(response.toJson)

      case _ @Method.GET -> Root / "articles" / slug =>
        MockData.articles.find(_.slug == slug) match {
          case Some(article) => Response.jsonString(ArticleResponse.fromDomain(article).toJson)
          case None          => Response.status(Status.NOT_FOUND)
        }
    }

}
