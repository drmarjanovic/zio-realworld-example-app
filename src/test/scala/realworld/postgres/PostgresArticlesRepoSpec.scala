package realworld.postgres

import io.getquill.Delete
import realworld.api.ArticlesResponseSpec.genArticle
import realworld.config.AppConfig
import realworld.postgres.QuillContext.quote
import realworld.{Article, ArticleId, ArticlesRepo}
import zio.test.Assertion._
import zio.test.TestAspect._
import zio.test._
import zio.{Task, ZIO}

import QuillContext._
object PostgresArticlesRepoSpec extends ZIOSpecDefault {

  def spec: Spec[Any, Throwable] =
    (suite("PostgresArticlesRepoSpec")(
      test("fetching all with default offset and limit") {
        for {
          articles   <- genArticle.runCollectN(50)
          articleIds <- ZIO.foreach(articles)(a => insert(a.title, a.slug, a.body, a.description))
          limit       = 20
          offset      = 0
          result     <- ArticlesRepo.fetchAll(limit, offset)
        } yield assert(result.map(_.id))(hasSameElements(articleIds.slice(offset, offset + limit).map(ArticleId)))
      },
      test("fetching all returns an empty list when offset is too large") {
        for {
          articles <- genArticle.runCollectN(50)
          _        <- ZIO.foreachDiscard(articles)(a => insert(a.title, a.slug, a.body, a.description))
          limit     = 20
          offset    = 51
          result   <- ArticlesRepo.fetchAll(limit, offset)
        } yield assert(result)(isEmpty)
      },
      test("finding existing article by slug") {
        val slug = "test-slug"
        for {
          articleId <- insert("test-title", slug, "test-body", "test-description")
          result    <- ArticlesRepo.findBySlug(slug)
        } yield assert(result)(isSome) && assertTrue(result.get.id == ArticleId(articleId)) && assertTrue(
          result.get.slug == slug
        )
      },
      test("finding non-existing article by slug") {
        val slug = "non-existing-test-slug"
        assertZIO(ArticlesRepo.findBySlug(slug))(isNone)
      }
    ) @@ before(cleanTable) @@ beforeAll(QuillContext.migrate.provide(AppConfig.live)))
      .provide(PostgresArticlesRepo.live) @@ sequential

  private def cleanTable: Task[Unit] = {
    val sql = quote {
      sql"""DELETE FROM articles;""".as[Delete[Unit]]
    }

    QuillContext.run(sql).unit.provide(QuillContext.live)
  }

  private def insert(title: String, slug: String, body: String, description: String): Task[Long] = {
    val sql = quote {
      query[Article]
        .insert(_.title -> lift(title), _.slug -> lift(slug), _.body -> lift(body), _.description -> lift(description))
        .returning(_.id)
    }

    QuillContext.run(sql).map(_.id).provide(QuillContext.live)
  }

}
