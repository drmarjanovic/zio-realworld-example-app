package realworld.api

import zhttp.http._
import zio.json._

object Application {

  final val Routes: Http[Any, Nothing, Request, UResponse] =
    Http.collect[Request] { case _ @Method.GET -> Root / "health" =>
      Response.jsonString(AppInfo.up.toJson)
    }

}
