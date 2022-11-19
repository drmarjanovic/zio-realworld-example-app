package realworld

import zio.test.{Gen, ZIOSpecDefault}

trait BaseSpec extends ZIOSpecDefault {

  def genArticle: Gen[Any, Article] =
    for {
      id          <- Gen.int(1, Int.MaxValue)
      title       <- Gen.stringBounded(1, 128)(Gen.alphaNumericChar)
      slug        <- Gen.uuid
      body        <- Gen.alphaNumericString
      description <- Gen.alphaNumericString
      createdAt   <- Gen.localDateTime
      updatedAt   <- Gen.localDateTime
    } yield Article(
      id = ArticleId(id),
      title = title,
      slug = slug.toString,
      body = body,
      description = description,
      createdAt = createdAt,
      updatedAt = updatedAt
    )

}
