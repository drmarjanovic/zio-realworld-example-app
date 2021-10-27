package com.github.drmarjanovic.app.domain

import java.time.LocalDateTime

final case class Article(
  id: ArticleId,
  slug: String,
  title: String,
  description: String,
  body: String,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)
