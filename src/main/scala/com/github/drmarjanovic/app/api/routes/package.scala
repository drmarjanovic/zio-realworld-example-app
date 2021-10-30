package com.github.drmarjanovic.app.api

import zhttp.http._
import zio.Chunk

import scala.util.Try

package object routes {

  def getQueryParam[A](req: Request, key: String): Option[A] =
    req.url.queryParams.get(key).flatMap(_.headOption).flatMap(value => Try(value.asInstanceOf[A]).toOption)

  def unprocessableEntity(data: String): UResponse = Response.http(
    Status.UNPROCESSABLE_ENTITY,
    content = HttpData.CompleteData(Chunk.fromArray(data.getBytes(HTTP_CHARSET))),
    headers = List(Header.contentTypeJson)
  )
}
