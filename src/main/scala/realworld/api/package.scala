package realworld

import zhttp.http._
import zio.Chunk
import zio.json.JsonEncoder
import zio.json.internal.Write

import java.time.LocalDateTime

package object api {

  implicit final class RequestOps(private val req: Request) extends AnyVal {
    def limit: Int = req.url.queryParams.get("limit").flatMap(_.headOption).flatMap(_.toIntOption).getOrElse(20)

    def offset: Int = req.url.queryParams.get("offset").flatMap(_.headOption).flatMap(_.toIntOption).getOrElse(0)
  }

  implicit val dateTimeEncoder: JsonEncoder[LocalDateTime] = (dt: LocalDateTime, _: Option[Int], out: Write) =>
    out.write(dt.toString)

  def unprocessableEntity(data: String): UResponse = Response.http(
    Status.UNPROCESSABLE_ENTITY,
    content = HttpData.CompleteData(Chunk.fromArray(data.getBytes(HTTP_CHARSET))),
    headers = List(Header.contentTypeJson)
  )

}
