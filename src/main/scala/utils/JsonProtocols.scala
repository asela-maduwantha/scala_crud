// src/main/scala/utils/JsonProtocols.scala

package utils

import models.Book
import spray.json.DefaultJsonProtocol._

object JsonProtocols {
  implicit val bookFormat = jsonFormat4(Book)
}
