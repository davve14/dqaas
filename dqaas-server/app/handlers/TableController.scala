package handlers

import javax.inject.Inject
import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.Await
import scala.concurrent._
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

case class PostFormInput(title: String, body: String)

/**
  * Takes HTTP requests and produces JSON.
  */
class TableController @Inject()(cc: TableControllerComponents)(implicit ec: ExecutionContext)
    extends TableBaseController(cc) {

  private val logger = Logger(getClass)

  private val form: Form[PostFormInput] = {
    import play.api.data.Forms._

    Form(
      mapping(
        "title" -> nonEmptyText,
        "body" -> text
      )(PostFormInput.apply)(PostFormInput.unapply)
    )
  }

  def index: Action[AnyContent] = TableAction.async { implicit request =>
    logger.trace("index: ")
    
    tableResourceHandler.getTables.map { tables =>
      Ok(Json.toJson(tables)).withHeaders("Access-Control-Allow-Origin" -> "*")
    }
    
  }

  def process: Action[AnyContent] = TableAction.async { implicit request =>
    logger.trace("process: ")
    processJsonPost()
  }

  def show(id: String): Action[AnyContent] = TableAction.async { implicit request =>
    logger.trace(s"show: id = $id")
    

    
    tableResourceHandler.getTable(id).map { table =>
      Ok(Json.toJson(table)).withHeaders("Access-Control-Allow-Origin" -> "*")
    }
  }

  private def processJsonPost[A]()(implicit request: TableRequest[A]): Future[Result] = {
    def failure(badForm: Form[PostFormInput]) = {
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(input: PostFormInput) = {
      tableResourceHandler.create(input).map { post =>
        Created(Json.toJson(post)).withHeaders(LOCATION -> post.link)
      }
    }

    form.bindFromRequest().fold(failure, success)
  }
}
