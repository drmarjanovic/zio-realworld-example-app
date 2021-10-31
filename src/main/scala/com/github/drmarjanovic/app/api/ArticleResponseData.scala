package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.Article
import zio.json._

import java.time.LocalDateTime

final case class ArticleResponseData(
  title: String,
  slug: String,
  body: String,
  description: String,
  @jsonField("tagList")
  tags: Set[String],
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)

object ArticleResponseData {
  implicit val encoder: JsonEncoder[ArticleResponseData] = DeriveJsonEncoder.gen[ArticleResponseData]

  def fromDomain(article: Article) =
    new ArticleResponseData(
      title = article.title,
      slug = article.slug,
      body = article.body,
      description = article.description,
      tags = Set.empty,
      createdAt = article.createdAt,
      updatedAt = article.updatedAt
    )
}
