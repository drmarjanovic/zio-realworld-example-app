package com.github.drmarjanovic.app.repo

import com.github.drmarjanovic.app.domain.Article
import zio._

trait ArticlesRepo {
  def all: Task[Chunk[Article]]
}

object ArticlesRepo extends Accessible[ArticlesRepo] {
  lazy val test: ULayer[Has[ArticlesRepo]] = InMemArticlesRepo.toLayer
}
