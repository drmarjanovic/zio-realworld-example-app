package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.Generators._
import zio.test.Assertion._
import zio.test._

object ArticleResponseDataSpec extends DefaultRunnableSpec {
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
