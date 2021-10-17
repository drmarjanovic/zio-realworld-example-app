package com.github.drmarjanovic.app.api.routes

import zhttp.http._

object Articles {

  val routes: Http[Any, Nothing, Request, UResponse] =
    Http.collect[Request] { case _ @Method.GET -> Root / "articles" =>
      Response.text("Hello world!")
    }

}
