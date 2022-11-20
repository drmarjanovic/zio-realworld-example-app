package realworld.api

import realworld.{ArticlesRepo, InMemArticlesRepo}
import zio.http.{Request, Response, URL}
import zio.json.EncoderOps
import zio.test.Assertion._
import zio.test.{ZIOSpecDefault, _}
import zio.{Scope, ZIO}

object ArticlesSpec extends ZIOSpecDefault {

  def spec: Spec[Environment with TestEnvironment with Scope, Any] =
    suite("ArticlesSpec")(
      test("successfully fetch article by slug")(
        for {
          articles <- ZIO.service[ArticlesRepo]
          article  <- articles.insert("test-title", "test-body", "test-description")
          res      <- Articles.Routes(Request.get(URL(BasePath / "articles" / "test-title")))
        } yield assert(res.toString)(equalTo(Response.json(ArticleResponse.fromDomain(article).toJson).toString))
      ),
      test("return a proper error response when article by slug does not exist")(
        assertZIO(Articles.Routes(Request.get(URL(BasePath / "articles" / "non-existing"))).map(_.toString))(
          equalTo(Response.json(ErrorResponse.fromReasons("Article non-existing does not exist.").toJson).toString)
        )
      )
    ).provide(InMemArticlesRepo.live)

}
