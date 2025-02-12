// src/main/scala/services/BookService.scala

package services

import models.{Book, Books}
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{Future, ExecutionContext}

class BookService(db: Database)(implicit ec: ExecutionContext) {

  def init(): Future[Unit] = db.run(Books.table.schema.createIfNotExists)

  def create(book: Book): Future[Int] =
    db.run((Books.table returning Books.table.map(_.id)) += book)

  def readAll(): Future[Seq[Book]] =
    db.run(Books.table.result)

  def readById(bookId: Int): Future[Option[Book]] =
    db.run(Books.table.filter(_.id === bookId).result.headOption)

  def update(bookId: Int, book: Book): Future[Int] =
    db.run(Books.table.filter(_.id === bookId).update(book.copy(id = Some(bookId))))

  def delete(bookId: Int): Future[Int] =
    db.run(Books.table.filter(_.id === bookId).delete)
}
