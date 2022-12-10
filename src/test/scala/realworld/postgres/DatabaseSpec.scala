package realworld.postgres

import io.getquill.Delete
import realworld.BaseSpec
import realworld.config.AppConfig
import realworld.postgres.QuillContext._
import zio.Task
import zio.test.TestAspect
import zio.test.TestAspect.{before, beforeAll}

trait DatabaseSpec extends BaseSpec {

  val setupDatabase: TestAspect[Nothing, Any, Throwable, Any] =
    beforeAll(QuillContext.migrate.provide(AppConfig.live)) @@ before(cleanTables)

  private def cleanTables: Task[Unit] = {
    val sql = quote(sql"""DELETE FROM articles;""".as[Delete[Unit]])
    QuillContext.run(sql).unit.provide(QuillContext.live)
  }

}
