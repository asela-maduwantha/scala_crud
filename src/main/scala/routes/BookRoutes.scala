package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import services.BookService
import utils.JsonProtocols._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Route
import models.Book

import scala.concurrent.ExecutionContext

class BookRoutes(bookService: BookService)(implicit ec: ExecutionContext) {

  val routes = pathPrefix("books") {
    pathEndOrSingleSlash {
      get{
        onSuccess(bookService.readAll()){ books : Seq[Book]=>
          complete((StatusCodes.OK, books))
        }

      }~
        post {
          entity(as[Book]) { book =>
            onSuccess(bookService.create(book)) { id =>
              complete((StatusCodes.Created, s"Book added with ID: $id"))
            }
          }
        }
    } ~
      path(IntNumber) { id =>
        get {
          onSuccess(bookService.readById(id)) {
            case Some(book) => complete(book)
            case None       => complete(StatusCodes.NotFound)
          }
        } ~
          put {
            entity(as[Book]) { book =>
              onSuccess(bookService.update(id, book)) {
                case 0 => complete((StatusCodes.NotFound, "Book not found"))
                case _ => complete(s"Book with ID $id updated")
              }
            }
          } ~
          delete {
            onSuccess(bookService.delete(id)) {
              case 0 => complete((StatusCodes.NotFound, "Book not found"))
              case _ => complete(s"Book with ID $id deleted")
            }
          }
      }
  }
}
