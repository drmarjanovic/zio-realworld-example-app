package com.github.drmarjanovic.app.api

import com.github.drmarjanovic.app.Article
import zio.json._

final case class ArticleResponse(article: ArticleResponseData) extends AnyVal

object ArticleResponse {
  implicit val encoder: JsonEncoder[ArticleResponse] = DeriveJsonEncoder.gen[ArticleResponse]

  def fromDomain(article: Article) =
    new ArticleResponse(article = ArticleResponseData.fromDomain(article))
}
