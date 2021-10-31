package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.Generators._
import zio.test.Assertion._
import zio.test._

object ArticleResponseSpec extends DefaultRunnableSpec {
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
