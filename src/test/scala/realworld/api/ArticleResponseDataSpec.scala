package realworld.api

import realworld.api.ArticlesResponseSpec.genArticle
import zio.Scope
import zio.test.Assertion.equalTo
import zio.test._

object ArticleResponseDataSpec extends ZIOSpecDefault {
  def spec: Spec[Environment with TestEnvironment with Scope, Any] =
    suite("ArticleResponseDataSpec")(
      test("fromDomain correctly transforms domain to response data") {
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
