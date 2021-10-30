package com.github.drmarjanovic.app.api.routes

import com.github.drmarjanovic.app.api.model.ArticlesResponse
import com.github.drmarjanovic.app.domain.ArticlesRepo
import zhttp.http._
import zio.Has
import zio.json._

object Articles {
  val routes: Http[Has[ArticlesRepo], Throwable, Request, UResponse] =
    Http.collectM[Request] { case req @ Method.GET -> Root / "articles" =>
      val limit  = req.url.queryParams.get("limit").flatMap(_.headOption).map(_.toInt).getOrElse(20)
      val offset = req.url.queryParams.get("offset").flatMap(_.headOption).map(_.toInt).getOrElse(0)

      ArticlesRepo(_.all(limit, offset)).map(data => ArticlesResponse.fromDomain(data).toJson).map(Response.jsonString)
    }
}
