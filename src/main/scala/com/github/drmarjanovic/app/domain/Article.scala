package com.github.drmarjanovic.app.domain

import org.joda.time.DateTime

final case class Article(
  id: ArticleId,
  slug: String,
  title: String,
  description: String,
  body: String,
  createdAt: DateTime,
  updatedAt: DateTime
)
