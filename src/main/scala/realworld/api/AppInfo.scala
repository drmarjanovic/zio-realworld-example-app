package realworld.api

import realworld.BuildInfo
import zio.json._

final case class AppInfo(name: String, version: String, description: String, status: String)

object AppInfo {
  val up = new AppInfo(BuildInfo.name, BuildInfo.version, BuildInfo.description, status = "up")

  implicit val encoder: JsonEncoder[AppInfo] = DeriveJsonEncoder.gen[AppInfo]
}
