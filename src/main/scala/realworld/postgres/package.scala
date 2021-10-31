package realworld

import io.getquill.mirrorContextWithQueryProbing.{querySchema, quote}
import io.getquill.{Query, mirrorContextWithQueryProbing}

package object postgres {
  val articles: mirrorContextWithQueryProbing.Quoted[Query[Article]] = quote {
    querySchema[Article]("articles", _.createdAt -> "created_at", _.updatedAt -> "updated_at")
  }
}
