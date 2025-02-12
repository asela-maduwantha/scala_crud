// src/main/scala/utils/JsonProtocols.scala

package utils

import models.Book
import spray.json.DefaultJsonProtocol._
import spray.json.{JsArray, JsValue, RootJsonFormat, deserializationError, enrichAny}

object JsonProtocols {
  implicit val bookFormat = jsonFormat4(Book)

  implicit val seqBookFormat: RootJsonFormat[Seq[Book]] = new RootJsonFormat[Seq[Book]] {
    override def read(json: JsValue): Seq[Book] = deserializationError("")

    override def write(seq: Seq[Book]): JsValue = JsArray(seq.map(_.toJson).toVector)
  }
}
