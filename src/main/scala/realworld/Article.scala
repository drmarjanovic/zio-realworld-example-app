package realworld

import java.time.LocalDateTime

final case class Article(
  id: ArticleId,
  title: String,
  slug: String,
  body: String,
  description: String,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)
