package com.github.drmarjanovic.app.repo
import com.github.drmarjanovic.app.domain.{Article, ArticleId, UserId}
import zio.{Chunk, Task}

import java.time.LocalDateTime

final case class InMemArticlesRepo() extends ArticlesRepo {
  override def all: Task[Chunk[Article]] = {
    val article = Article(
      ArticleId(1),
      UserId(1),
      "slug-1",
      "title-1",
      "desc-1",
      "body-1",
      Set("tag1", "tag2"),
      LocalDateTime.now,
      LocalDateTime.now
    )

    Task.succeed(Chunk.fromIterable(List(article)))
  }
}
