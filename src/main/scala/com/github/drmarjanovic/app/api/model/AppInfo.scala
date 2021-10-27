package com.github.drmarjanovic.app.api.model

import com.github.drmarjanovic.app.BuildInfo
import zio.json._

final case class AppInfo(name: String, version: String, description: String, status: String)

object AppInfo {
  val up = new AppInfo(BuildInfo.name, BuildInfo.version, BuildInfo.description, status = "up")

  implicit val encoder: JsonEncoder[AppInfo] = DeriveJsonEncoder.gen[AppInfo]
}
