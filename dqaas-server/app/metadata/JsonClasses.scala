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

//START HiveTable
case class TableAttributes(
  name: String,
  owner: String,
  qualifiedName: String
)
case class Entity(
  guid: String,
  typeName: String,
  attributes: TableAttributes
)

case class Source(
  referredEntities: List[ReferredEntity],
  entity: Entity
)

case class HiveColumn(
  typeName: String,
  name: String,
  dataType: String
)
case class ColumnAttributes(
  owner: String
)

case class ReferredEntityAttributes(
  name: Option[String] = None,
  qualifiedName: String,
  `type`: Option[String] = None
)

case class ReferredEntity(
  guid: String,
  typeName: String,
  attributes: ReferredEntityAttributes
)

case class HiveTable(
  guid: String,
  typeName: String,
  name: String,
  hiveColumns: List[ReferredEntity]
)

object HiveTable {
  implicit val implicitWritesReferredEntityAttributes = new Writes[ReferredEntityAttributes] {
    def writes(referredEntityAttributes: ReferredEntityAttributes) =
      Json.obj(
        "name" -> referredEntityAttributes.name,
        "qualifiedName" -> referredEntityAttributes.qualifiedName,
        "type" -> referredEntityAttributes.`type`
      )
  }
  
  implicit val implicitWritesReferredEntity = new Writes[ReferredEntity] {
    def writes(referredEntity: ReferredEntity) =
      Json.obj(
        "guid" -> referredEntity.guid,
        "typeName" -> referredEntity.typeName,
        "attributes" -> referredEntity.attributes
      )
  }
  implicit val implicitWritesHiveTable = new Writes[HiveTable] {
    def writes(tableSource: HiveTable) =
      Json.obj(
        "guid" -> tableSource.guid,
        "name" -> tableSource.name,
        "entityTypeName" -> tableSource.typeName,
        "referredEntities" -> tableSource.hiveColumns
      )
  }
}

// END HiveTable

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
  implicit val TableAttributesFormat = jsonFormat3(TableAttributes.apply)  
  implicit val EntityFormat = jsonFormat3(Entity.apply)
  implicit val ColumnAttributesFormat = jsonFormat1(ColumnAttributes.apply)
  implicit val ReferredEntityAttributesFormat = jsonFormat3(ReferredEntityAttributes.apply)
  implicit val ReferredEntityFormat = jsonFormat3(ReferredEntity.apply)
  
  implicit object SourceJsonFormat extends RootJsonReader[Source] {
    def read(value: spray.json.JsValue) = value.asJsObject.getFields("referredEntities", "entity") match {
      case Seq(referredEntities, entity) => Source(
          referredEntities.asJsObject.fields.toList.map(item => ReferredEntity(item._1, ReferredEntityFormat.read(item._2).typeName,ReferredEntityFormat.read(item._2).attributes)),
          entity.convertTo[Entity]
      )
    }
  }
}
  

