package realworld

import zio.random.Random
import zio.test.{DefaultRunnableSpec, Gen, Sized}

trait BaseSpec extends DefaultRunnableSpec {

  def genArticle: Gen[Random with Sized, Article] =
    Gen.zipN(
      Gen.anyInt,
      Gen.anyString,
      Gen.anyString,
      Gen.anyString,
      Gen.anyString,
      Gen.anyLocalDateTime,
      Gen.anyLocalDateTime
    )((id, title, slug, body, description, createdAt, updatedAt) =>
      Article(
        id = ArticleId(id),
        title = title,
        slug = slug,
        body = body,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
      )
    )

}
