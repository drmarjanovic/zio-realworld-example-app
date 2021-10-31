package realworld.api

import realworld.BaseSpec
import zio.test.Assertion._
import zio.test._

object ArticleResponseSpec extends BaseSpec {
  def spec: ZSpec[Environment, Failure] = suite("ArticleResponseSpec")(
    testM("fromDomain correctly transforms domain to response") {
      check(genArticle) { article =>
        val result = ArticleResponse.fromDomain(article)

        val expected = ArticleResponse(ArticleResponseData.fromDomain(article))

        assert(result)(equalTo(expected))
      }
    }
  )
}
