package com.github.drmarjanovic.app.api.model

import com.github.drmarjanovic.app.domain.Article
import org.joda.time.DateTime
import zio.json._

final case class ArticleResponseData(
  title: String,
  slug: String,
  body: String,
  description: String,
  @jsonField("tagList")
  tags: List[String],
  createdAt: DateTime,
  updatedAt: DateTime
)

object ArticleResponseData {
  implicit val encoder: JsonEncoder[ArticleResponseData] = DeriveJsonEncoder.gen[ArticleResponseData]

  def fromDomain(article: Article) =
    new ArticleResponseData(
      title = article.title,
      slug = article.slug,
      body = article.body,
      description = article.description,
      tags = Nil,
      createdAt = article.createdAt,
      updatedAt = article.updatedAt
    )
}
