package realworld.api

import realworld.BaseSpec
import zio.Scope
import zio.test.Assertion.equalTo
import zio.test._

object ArticlesResponseSpec extends BaseSpec {
  def spec: Spec[Environment with TestEnvironment with Scope, Any] = suite("ArticlesResponseSpec")(
    test("fromDomain correctly transforms list of articles to response") {
      check(Gen.listOf(genArticle)) { articles =>
        val result = ArticlesResponse.fromDomain(articles)

        val expected = ArticlesResponse(
          count = articles.size,
          articles = articles.map(ArticleResponseData.fromDomain)
        )

        assert(result)(equalTo(expected))
      }
    }
  )
}
