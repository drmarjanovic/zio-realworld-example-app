package realworld.postgres

import realworld.config.AppConfig
import realworld.{ArticlesRepo, BaseSpec}
import zio.magic._
import zio.test.Assertion._
import zio.test.TestAspect._
import zio.test._

object PostgresArticlesRepoSpec extends BaseSpec {

  def spec: ZSpec[Environment, Failure] = suite("PostgresArticlesRepoSpec")(
    testM("fetching all") {
      checkM(Gen.listOf(genArticle), Gen.int(0, 100), Gen.int(0, 100)) { (articles, limit, offset) =>
        for {
          result <- ArticlesRepo(_.fetchAll(limit, offset)).inject(QuillContext.live, ArticlesRepo.live)
        } yield assert(result)(equalTo(articles.slice(offset, offset + limit)))
      }
    } @@ ignore,
    testM("finding by slug") {
      checkM(genArticle) { article =>
        for {
          saved <- ArticlesRepo(_.insert(article.title, article.body, article.description))
                     .inject(QuillContext.live, ArticlesRepo.live)
          result <- ArticlesRepo(_.findBySlug(saved.slug)).inject(QuillContext.live, ArticlesRepo.live)
        } yield assert(result)(isSome(equalTo(saved)))
      }
    }
  ) @@ beforeAll(QuillContext.migrate.inject(AppConfig.live))

}
