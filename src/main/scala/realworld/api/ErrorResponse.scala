package realworld.api

import zio.Chunk
import zio.json._

final case class ErrorResponse(errors: ErrorResponseData) extends AnyVal

object ErrorResponse {
  implicit val encoder: JsonEncoder[ErrorResponse] = DeriveJsonEncoder.gen[ErrorResponse]

  def fromReasons(reasons: String*): ErrorResponse =
    new ErrorResponse(ErrorResponseData(Chunk.fromIterable(reasons)))
}
