package realworld.api

import zio.http._
import zio.http.model.Method
import zio.json._

object Application {

  final val Routes: Http[Any, Nothing, Request, Response] =
    Http.collect[Request] { case Method.GET -> !! / "health" =>
      Response.json(AppInfo.up.toJson)
    }

}
