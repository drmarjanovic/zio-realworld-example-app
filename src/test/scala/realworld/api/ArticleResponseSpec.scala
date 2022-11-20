package realworld.api

import realworld.api.ArticlesResponseSpec.genArticle
import zio.Scope
import zio.test.Assertion.equalTo
import zio.test._

object ArticleResponseSpec extends ZIOSpecDefault {
  def spec = suite("ArticleResponseSpec")(
    test("fromDomain correctly transforms domain to response") {
      check(genArticle) { article =>
        val result = ArticleResponse.fromDomain(article)

        val expected = ArticleResponse(ArticleResponseData.fromDomain(article))

        assert(result)(equalTo(expected))
      }
    }
  )
}
