package metadata

import spray.json._
import play.api.libs.json._

case class IpInfo(ip: String)

case class SearchParameters(
  typeName: String,
  excludeDeletedEntities: Boolean,
  includeClassificationAttributes: Boolean,
  limit: Double,
  offset: Double
)
case class Attributes(
  owner: String,
  createTime: Double,
  qualifiedName: String,
  name: String,
  description: Option[String] = None
)
case class Entities(
  typeName: String,
  attributes: Attributes,
  guid: String,
  status: String,
  displayText: String,
  classificationNames: List[String]
)
case class R00tJsonObject(
  queryType: String,
  searchParameters: SearchParameters,
  entities: List[Entities]
)

object Entities {
  implicit val implicitWritesHiveTables = new Writes[Entities] {
    def writes(table: Entities) =
      Json.obj(
        "typeName" -> table.typeName,
        "name" -> table.attributes.name,
        "guid"-> table.guid,
        "status"-> table.status,
        "displayText" -> table.displayText,
        "owner" -> table.attributes.owner
      )
  }
}

object JsonProtocol extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(IpInfo.apply)
  implicit val SearchParametersFormat = jsonFormat5(SearchParameters)
  implicit val AttributesFormat = jsonFormat5(Attributes)
  implicit val EntitiesFormat = jsonFormat6(Entities.apply)
  implicit val R00tJsonObjectFormat = jsonFormat3(R00tJsonObject.apply)
  
}
  

