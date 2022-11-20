package realworld.api

import zio.json.{DeriveJsonDecoder, JsonDecoder, jsonField}

final case class CreateArticleRequestData(
  title: String,
  description: String,
  body: String,
  @jsonField("tagList")
  tags: Set[String]
)

object CreateArticleRequestData {
  implicit val decoder: JsonDecoder[CreateArticleRequestData] = DeriveJsonDecoder.gen[CreateArticleRequestData]
}
