package com.github.drmarjanovic.app.api

import org.joda.time.DateTime
import zio.json.internal.Write
import zio.json.JsonEncoder

package object model {
  implicit val dateTimeEncoder: JsonEncoder[DateTime] = (dt: DateTime, _: Option[Int], out: Write) =>
    out.write(dt.toString)
}
