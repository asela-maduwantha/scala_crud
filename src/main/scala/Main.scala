import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import services.BookService
import routes.BookRoutes
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContextExecutor

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("bookSystem")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val db = Database.forConfig("slick.dbs.default.db") // Ensure this matches your application.conf path
  val bookService = new BookService(db)
  bookService.init()

  val routes = new BookRoutes(bookService).routes

  Http().newServerAt("localhost", 8080).bind(routes)
}
