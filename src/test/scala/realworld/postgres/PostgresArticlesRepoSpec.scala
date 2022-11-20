package realworld.postgres

import realworld.api.ArticlesResponseSpec.genArticle
import realworld.config.AppConfig
import realworld.{Article, ArticlesRepo}
import zio.ZIO
import zio.test.Assertion._
import zio.test.TestAspect._
import zio.test._
object PostgresArticlesRepoSpec extends ZIOSpecDefault {

//  val a: Quoted[(String, String, String, String) => Query[Int]] = quote {
//    (title: String, slug: String, body: String, description: String) =>
//      sql"""INSERT INTO articles(title, slug, body, description) VALUES ($title, $slug, $body, $description) RETURNING id;"""
//        .as[Query[Int]]
//  }

//  ctx.run(a)

  def spec: Spec[Any, Throwable] = (suite("PostgresArticlesRepoSpec")(
    test("fetching all") {
      check(Gen.listOf(genArticle), Gen.int(0, 100), Gen.int(0, 100)) { (articles, limit, offset) =>
        for {
          repo   <- ZIO.service[ArticlesRepo]
          result <- repo.fetchAll(limit, offset)
        } yield assert(result)(hasSameElements(articles.slice(offset, offset + limit)))
      }
    } @@ ignore,
    test("finding by slug") {
      check(genArticle) { article =>
        for {
          repo   <- ZIO.service[ArticlesRepo]
          saved  <- repo.insert(article.title, article.body, article.description)
          result <- repo.findBySlug(saved.slug)
        } yield assert(result)(isSome[Article](equalTo(saved)))
      }
    }
//    test("inserting") {},
//    test("deleting by slug") {}
  ) @@ beforeAll(QuillContext.migrate.provide(AppConfig.live))).provide(ArticlesRepo.live)

}
