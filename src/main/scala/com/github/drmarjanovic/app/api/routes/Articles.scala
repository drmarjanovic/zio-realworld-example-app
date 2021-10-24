package com.github.drmarjanovic.app.api.routes

import com.github.drmarjanovic.app.api.model.ArticlesResponse
import com.github.drmarjanovic.app.repo.ArticlesRepo
import zhttp.http._
import zio.Has
import zio.json._

object Articles {
  val routes: Http[Has[ArticlesRepo], Throwable, Request, UResponse] =
    Http.collectM[Request] { case _ @Method.GET -> Root / "articles" =>
      ArticlesRepo(_.all).map(data => ArticlesResponse.fromDomain(data.toList).toJson).map(Response.jsonString)
    }
}
