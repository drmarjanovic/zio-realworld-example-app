package realworld

import zio.http.model.{HeaderNames, HeaderValues, Headers, Status}
import zio.http.{!!, Body, Path, Request, Response}
import zio.json.internal.Write
import zio.json.{EncoderOps, JsonEncoder}

import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneOffset, ZonedDateTime}

package object api {

  val BasePath: Path = !! / "api"

  implicit final class RequestOps(private val req: Request) extends AnyVal {
    def limit: Int = req.url.queryParams.get("limit").flatMap(_.headOption).flatMap(_.toIntOption).getOrElse(20)

    def offset: Int = req.url.queryParams.get("offset").flatMap(_.headOption).flatMap(_.toIntOption).getOrElse(0)
  }

  def unexpectedErrorFrom(reasons: String*): Response =
    Response(
      status = Status.UnprocessableEntity,
      headers = Headers(HeaderNames.contentType, HeaderValues.applicationJson),
      body = Body.fromCharSequence(ErrorResponse.fromReasons(reasons: _*).toJson)
    )

  implicit val dateTimeEncoder: JsonEncoder[LocalDateTime] = (dt: LocalDateTime, _: Option[Int], out: Write) =>
    out.write(s"\"${ZonedDateTime.of(dt, ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)}\"")

}
