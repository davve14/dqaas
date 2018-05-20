package metadata

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

object HiveTableHandler {
  import JsonProtocol._
  
  implicit val system = ActorSystem("Request-Level")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  
  def getAtlasHiveTables(): Future[R00tJsonObject] = {

    implicit val system = ActorSystem()

    val authorization = headers.Authorization(BasicHttpCredentials("holger_gov","holger_gov"))
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://localhost:21000/api/atlas/v2/search/basic?typeName=hive_table", headers = List(authorization)))
    
    val result: Future[R00tJsonObject] = responseFuture flatMap { res =>
      Unmarshal(res.entity).to[R00tJsonObject]
    }
    result
  }
  
  def getBody(futureResponse: Future[HttpResponse], timeout: FiniteDuration = 5.seconds): String = {
    val response = Await.result(futureResponse, timeout)
    
    val bs: Future[ByteString] = response.entity.toStrict(timeout).map { _.data }
    val futureBody: Future[String] = bs.map(_.utf8String) // if you indeed need a `String`
    Await.result(futureBody, timeout)
  }
  
  def createHiveTable(name: String, ddl: String, location: String, external: Boolean): Unit = {
    println("Creating hive table")
  }
}





