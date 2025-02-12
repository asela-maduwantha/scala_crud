// src/main/scala/models/Book.scala

package models

import slick.jdbc.PostgresProfile.api._

case class Book(id: Option[Int], title: String, author: String, publishedYear: Int)

class Books(tag: Tag) extends Table[Book](tag, "books") {
  def id            = column[Int]("id", O.PrimaryKey, O.AutoInc)
  private def title         = column[String]("title")
  private def author        = column[String]("author")
  private def publishedYear = column[Int]("published_year")

  def * = (id.?, title, author, publishedYear) <> ((Book.apply _).tupled, Book.unapply)
}

object Books {
  val table = TableQuery[Books]
}
