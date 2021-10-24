package com.github.drmarjanovic.app.api

import zio.json.JsonEncoder
import zio.json.internal.Write

import java.time.LocalDateTime

package object model {
  implicit val dateTimeEncoder: JsonEncoder[LocalDateTime] = (dt: LocalDateTime, _: Option[Int], out: Write) =>
    out.write(dt.toString)
}
