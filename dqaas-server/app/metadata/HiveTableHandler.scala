package metadata

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{ Failure, Success }
import akka.stream.ActorMaterializer

class HiveTableHandler() {
  
  def createHiveTable(name: String, ddl: String, location: String, external: Boolean): Unit = {
    println("Creating hive table")
    
  }
  
  def getAtlasHiveTables(): String = {

    implicit val system = ActorSystem()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    implicit val materializer = ActorMaterializer()
    
    val authorization = headers.Authorization(BasicHttpCredentials("holger_gov","holger_gov"))
    
    val http = Http()
    
    val responseFuture: Future[HttpResponse] = http.singleRequest(HttpRequest(uri = "http://localhost:21000/api/atlas/v2/search/basic?typeName=hive_table", headers = List(authorization)))

    responseFuture
      .onComplete {
        case Success(res) => {
        val strictEntity: Future[HttpEntity.Strict] = res.entity.toStrict(3.seconds)
        strictEntity.onComplete {
          case Success(s) => {
            return println(s.toStrict(3.seconds)).toString()
          }
          case Failure(f) => {
            println("An error has occurred: " + f.getMessage)
          }
        }
        }
        case Failure(t) => {
          println("An error has occurred: " + t.getMessage)
          return t.toString()
        }
      }
    return "Not completed"

  }
}
