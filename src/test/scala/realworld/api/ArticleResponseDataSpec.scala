package realworld.api

import realworld.BaseSpec
import zio.test.Assertion._
import zio.test._

object ArticleResponseDataSpec extends BaseSpec {
  def spec: ZSpec[Environment, Failure] = suite("ArticleResponseDataSpec")(
    testM("fromDomain correctly transforms domain to response data") {
      check(genArticle) { article =>
        val result = ArticleResponseData.fromDomain(article)

        val expected = ArticleResponseData(
          title = article.title,
          slug = article.slug,
          body = article.body,
          description = article.description,
          tags = Set.empty,
          createdAt = article.createdAt,
          updatedAt = article.updatedAt
        )

        assert(result)(equalTo(expected))
      }
    }
  )
}
