package realworld.api

import realworld.api.ArticlesResponseSpec.genArticle
import zio.Scope
import zio.test.Assertion.equalTo
import zio.test._

object ArticleResponseSpec extends ZIOSpecDefault {
  def spec: Spec[Environment with TestEnvironment with Scope, Any] = suite("ArticleResponseSpec")(
    test("fromDomain correctly transforms domain to response") {
      check(genArticle) { article =>
        val result = ArticleResponse.fromDomain(article)

        val expected = ArticleResponse(ArticleResponseData.fromDomain(article))

        assert(result)(equalTo(expected))
      }
    }
  )
}
