package com.github.drmarjanovic.app.api.model

import com.github.drmarjanovic.app.domain.{ Article, ArticleId }
import org.joda.time.DateTime
import zio.test._
import zio.test.Assertion._
import zio.test.DefaultRunnableSpec

object ArticleResponseDataSpec extends DefaultRunnableSpec {
  def spec = suite("ArticleResponseDataSpec")(
    test("fromDomain correctly transforms domain to response data") {
      val now     = DateTime.now
      val article = Article(
        id = ArticleId(1),
        slug = "test-slug",
        title = "test-title",
        description = "test-description",
        body = "test-body",
        createdAt = now,
        updatedAt = now
      )

      val result = ArticleResponseData.fromDomain(article)

      val expected = ArticleResponseData(
        title = "test-title",
        slug = "test-slug",
        body = "test-body",
        description = "test-description",
        tags = Nil,
        createdAt = now,
        updatedAt = now
      )

      assert(result)(equalTo(expected))
    }
  )
}