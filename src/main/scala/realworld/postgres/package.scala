package realworld

import io.getquill.mirrorContextWithQueryProbing.{querySchema, quote}
import io.getquill.{EntityQuery, Quoted}

package object postgres {
  val articles: Quoted[EntityQuery[Article]] = quote {
    querySchema[Article]("articles", _.id -> "article_id", _.createdAt -> "created_at", _.updatedAt -> "updated_at")
  }
}
