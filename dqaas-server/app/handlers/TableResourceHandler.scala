package handlers

import javax.inject.{Inject, Provider}
import play.api.MarkerContext
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._
import metadata._
import akka.http.scaladsl.model._

/**
  * DTO for displaying post information.
  */
case class TableResource(id: String, link: String, title: String, body: String)

object TableResource {

  /**
    * Mapping to write a PostResource out as a JSON value.
    */
  implicit val implicitWrites = new Writes[TableResource] {
    def writes(post: TableResource): JsValue = {
      Json.obj(
        "id" -> post.id,
        "link" -> post.link,
        "title" -> post.title,
        "body" -> post.body
      )
    }
  }
}

/**
  * Controls access to the backend data, returning [[PostResource]]
  */
class TableResourceHandler @Inject()(
    routerProvider: Provider[TableRouter],
    tableRepository: PostRepository)(implicit ec: ExecutionContext) {

  def create(postInput: PostFormInput)(implicit mc: MarkerContext): Future[TableResource] = {
    val data = PostData(PostId("999"), postInput.title, postInput.body)
    // We don't actually create the post, so return what we have
    tableRepository.create(data).map { id =>
      createTableResource(data)
    }
  }

  def lookup(id: String)(implicit mc: MarkerContext): Future[Option[TableResource]] = {
    val tableFuture = tableRepository.get(PostId(id))
    tableFuture.map { maybePostData =>
      maybePostData.map { postData =>
        createTableResource(postData)
      }
    }
  }

  def find(implicit mc: MarkerContext): Future[Iterable[TableResource]] = {
    tableRepository.list().map { postDataList =>
      postDataList.map(postData => createTableResource(postData))
    }
  }

  def getTables(): String = {
    println("getting from atlas")
    val atlas = new HiveTableHandler()
    atlas.getAtlasHiveTables()
  }
  
  private def createTableResource(p: PostData): TableResource = {
    TableResource(p.id.toString, routerProvider.get.link(p.id), p.title, p.body)
  }

}
