package realworld.api

import zio.Chunk
import zio.json._

final case class ErrorResponseData(body: Chunk[String])

object ErrorResponseData {
  implicit val encoder: JsonEncoder[ErrorResponseData] = DeriveJsonEncoder.gen[ErrorResponseData]
}
