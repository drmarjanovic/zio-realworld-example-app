package realworld.api

import zio.Scope
import zio.json._
import zio.test.Assertion.{equalTo, isLeft, isRight}
import zio.test._

object CreateArticleRequestSpec extends ZIOSpecDefault {

  def spec: Spec[Environment with TestEnvironment with Scope, Any] =
    suite("CreateArticleRequestSpec")(
      test("successfully transforms JSON to CreateArticleRequest") {
        val json =
          """
            |{
            |  "article": {
            |    "title": "test title",
            |    "description": "test description",
            |    "body": "test body",
            |    "tagList": ["tag a", "tag b"]
            |  }
            |}
            |""".stripMargin

        val result = json.fromJson[CreateArticleRequest]
        val expected = CreateArticleRequest(article =
          CreateArticleRequestData(
            title = "test title",
            description = "test description",
            body = "test body",
            tags = Set("tag a", "tag b")
          )
        )

        assert(result)(isRight(equalTo(expected)))
      },
      test("successfully transforms JSON to CreateArticleRequest without duplicate tags") {
        val json =
          """
            |{
            |  "article": {
            |    "title": "test title",
            |    "description": "test description",
            |    "body": "test body",
            |    "tagList": ["tag a", "tag b", "tag a", "tag a", "tag b", "tag c"]
            |  }
            |}
            |""".stripMargin

        val result = json.fromJson[CreateArticleRequest]
        val expected = CreateArticleRequest(article =
          CreateArticleRequestData(
            title = "test title",
            description = "test description",
            body = "test body",
            tags = Set("tag a", "tag b", "tag c")
          )
        )

        assert(result)(isRight(equalTo(expected)))
      },
      test("fail converting to CreateArticleRequest if one of the fields is missing") {
        val json =
          """
            |{
            |  "article": {
            |    "description": "test description",
            |    "body": "test body",
            |    "tagList": ["tag a", "tag b", "tag a", "tag a", "tag b", "tag c"]
            |  }
            |}
            |""".stripMargin

        val result = json.fromJson[CreateArticleRequest]
        assert(result)(isLeft)
      }
    )

}
