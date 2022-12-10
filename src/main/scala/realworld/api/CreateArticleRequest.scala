package realworld.api

import zio.json.{DeriveJsonDecoder, JsonDecoder}

final case class CreateArticleRequest(article: CreateArticleRequestData)

object CreateArticleRequest {
  implicit val decoder: JsonDecoder[CreateArticleRequest] = DeriveJsonDecoder.gen[CreateArticleRequest]
}
