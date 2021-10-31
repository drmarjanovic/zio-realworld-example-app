package realworld.api

import realworld.Article
import zio.json._

final case class ArticlesResponse(
  @jsonField("articlesCount")
  count: Int,
  articles: List[ArticleResponseData]
)

object ArticlesResponse {
  implicit val encoder: JsonEncoder[ArticlesResponse] = DeriveJsonEncoder.gen[ArticlesResponse]

  def fromDomain(articles: List[Article]) =
    new ArticlesResponse(
      count = articles.size,
      articles = articles.map(ArticleResponseData.fromDomain)
    )
}