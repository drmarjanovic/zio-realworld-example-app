package com.github.drmarjanovic.app

import com.github.drmarjanovic.app.domain.Article
import io.getquill.mirrorContextWithQueryProbing.{querySchema, quote}
import io.getquill.{Query, mirrorContextWithQueryProbing}

package object pg {
  val articles: mirrorContextWithQueryProbing.Quoted[Query[Article]] = quote {
    querySchema[Article]("articles", _.createdAt -> "created_at", _.updatedAt -> "updated_at")
  }
}
