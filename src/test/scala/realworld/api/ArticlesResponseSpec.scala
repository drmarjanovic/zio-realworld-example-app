package realworld.api

import realworld.BaseSpec
import zio.test.Assertion._
import zio.test._

object ArticlesResponseSpec extends BaseSpec {
  def spec: ZSpec[Environment, Failure] = suite("ArticlesResponseSpec")(
    testM("fromDomain correctly transforms list of articles to response") {
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
