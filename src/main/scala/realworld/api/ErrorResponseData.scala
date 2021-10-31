package realworld.api

import zio.json._

final case class ErrorResponseData(body: List[String])

object ErrorResponseData {
  implicit val encoder: JsonEncoder[ErrorResponseData] = DeriveJsonEncoder.gen[ErrorResponseData]
}
