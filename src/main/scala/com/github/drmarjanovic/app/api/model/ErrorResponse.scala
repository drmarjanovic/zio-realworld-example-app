package com.github.drmarjanovic.app.api.model
import zio.json._

final case class ErrorResponse(errors: ErrorResponseData) extends AnyVal

object ErrorResponse {
  implicit val encoder: JsonEncoder[ErrorResponse] = DeriveJsonEncoder.gen[ErrorResponse]

  def withReasons(reasons: String*): ErrorResponse =
    new ErrorResponse(ErrorResponseData(reasons.toList))
}
