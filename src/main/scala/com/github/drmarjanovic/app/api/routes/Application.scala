package com.github.drmarjanovic.app.api.routes

import com.github.drmarjanovic.app.api.model.AppInfo
import zhttp.http._
import zio.json._

object Application {

  val routes: Http[Any, Nothing, Request, UResponse] =
    Http.collect[Request] { case _ @Method.GET -> Root / "health" =>
      Response.jsonString(AppInfo.up.toJson)
    }

}
