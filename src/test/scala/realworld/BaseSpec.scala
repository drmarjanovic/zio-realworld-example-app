package realworld

import zio.random.Random
import zio.test.{DefaultRunnableSpec, Gen, Sized}

trait BaseSpec extends DefaultRunnableSpec {

  def genArticle: Gen[Random with Sized, Article] =
    Gen.zipN(
      Gen.int(1, Int.MaxValue),
      Gen.stringBounded(1, 128)(Gen.alphaNumericChar),
      Gen.anyUUID,
      Gen.alphaNumericString,
      Gen.alphaNumericString,
      Gen.anyLocalDateTime,
      Gen.anyLocalDateTime
    )((id, title, slug, body, description, createdAt, updatedAt) =>
      Article(
        id = ArticleId(id),
        title = title,
        slug = slug.toString,
        body = body,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
      )
    )

}
