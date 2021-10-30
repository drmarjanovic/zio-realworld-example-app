package com.github.drmarjanovic.app.api

import zhttp.http.{HTTP_CHARSET, Header, HttpData, Response, Status, UResponse}
import zio.Chunk

package object routes {
  def unprocessableEntity(data: String): UResponse = Response.http(
    Status.UNPROCESSABLE_ENTITY,
    content = HttpData.CompleteData(Chunk.fromArray(data.getBytes(HTTP_CHARSET))),
    headers = List(Header.contentTypeJson)
  )
}
