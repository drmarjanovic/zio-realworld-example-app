package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.{Article, ArticleId}
import zio.test.Assertion._
import zio.test._

object ArticleResponseDataSpec extends DefaultRunnableSpec {
  def spec: ZSpec[Environment, Failure] = suite("ArticleResponseDataSpec")(
    testM("fromDomain correctly transforms domain to response data") {
      check(Gen.anyString, Gen.anyString, Gen.anyString, Gen.anyString, Gen.anyLocalDateTime, Gen.anyLocalDateTime) {
        (title, slug, body, description, createdAt, updatedAt) =>
          val article = Article(ArticleId(1), title, slug, body, description, createdAt, updatedAt)
          val result  = ArticleResponseData.fromDomain(article)

          val expected = ArticleResponseData(
            title = title,
            slug = slug,
            body = body,
            description = description,
            tags = Set.empty,
            createdAt = createdAt,
            updatedAt = updatedAt
          )

          assert(result)(equalTo(expected))
      }
    }
  )
}
