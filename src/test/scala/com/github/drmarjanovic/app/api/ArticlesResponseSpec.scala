package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.Generators._
import zio.test.Assertion._
import zio.test._

object ArticlesResponseSpec extends DefaultRunnableSpec {
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
