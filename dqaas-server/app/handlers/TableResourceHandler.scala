package handlers

import javax.inject.{Inject, Provider}
import play.api.MarkerContext
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._
import metadata._


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

  def getTables(): Future[Iterable[Entities]] = {
    println("getting from atlas")
    //val DQ = new TableDQHandler()
    //DQ.runTDQEvaluation()
    val futureListHiveTables: Future[Iterable[Entities]] = HiveTableHandler.getAtlasHiveTables() map { iterable_entities =>
      iterable_entities.entities
    }
    futureListHiveTables
  }
  
  def getTable(tableGuid: String): Future[HiveTable] = {
    println("getting from atlas table")
    
    val futureHiveTable: Future[Source] = HiveTableHandler.getAtlasHiveTable(tableGuid) map { root =>
      root
    }
    
    val FinalHiveTable = futureHiveTable.map { fht =>
      HiveTable(fht.entity.guid,fht.entity.typeName,fht.entity.attributes.name,fht.referredEntities)
    }
    FinalHiveTable
    
  }
  
  private def createTableResource(p: PostData): TableResource = {
    TableResource(p.id.toString, routerProvider.get.link(p.id), p.title, p.body)
  }

}
