package com.github.drmarjanovic.app

import zhttp.http._
import zio.Chunk
import zio.json.JsonEncoder
import zio.json.internal.Write

import java.time.LocalDateTime
import scala.util.Try

package object api {
  implicit val dateTimeEncoder: JsonEncoder[LocalDateTime] = (dt: LocalDateTime, _: Option[Int], out: Write) =>
    out.write(dt.toString)

  def getQueryParam[A](req: Request, key: String): Option[A] =
    req.url.queryParams.get(key).flatMap(_.headOption).flatMap(value => Try(value.asInstanceOf[A]).toOption)

  def unprocessableEntity(data: String): UResponse = Response.http(
    Status.UNPROCESSABLE_ENTITY,
    content = HttpData.CompleteData(Chunk.fromArray(data.getBytes(HTTP_CHARSET))),
    headers = List(Header.contentTypeJson)
  )
}
