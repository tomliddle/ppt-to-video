package util

import play.api.libs.json.{Json, OFormat}
import services.Slide


object Implicits {

  implicit val slideJ: OFormat[Slide] = Json.format[Slide]
}
