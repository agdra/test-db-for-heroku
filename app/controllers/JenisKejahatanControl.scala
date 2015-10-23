package controllers

import javax.inject.Inject
import models.JenisKejahatan
import models.services.JenisKejahatanService
import play.api.cache.Cached
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.{SuccessResponse, ErrorResponse}
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class JenisKejahatanControl @Inject()(jkService: JenisKejahatanService, val messagesApi: MessagesApi, cached: Cached)
  extends Controller with I18nSupport {
    def list = Action.async { implicit request =>
      jkService.list.map {
        case jk: Seq[models.JenisKejahatan] => Ok(Json.toJson(SuccessResponse(jk)))
        case _ => NotFound(Json.toJson(ErrorResponse(NOT_FOUND, messagesApi("jenis.kejahatan.not.found"))))
      }
    }

    def findByID(id: Int) = Action.async { implicit request =>
      jkService.findByID(id).map {
        case Some(jk) => Ok(Json.toJson(SuccessResponse(jk)))
        case None => NotFound(Json.toJson(ErrorResponse(NOT_FOUND, messagesApi("jenis.kejahatan.not.found"))))
      }
    }

  }
