package utils

import javax.inject.Inject
import javax.inject._
import play.api.http.DefaultHttpErrorHandler
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.routing.Router
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ErrorHandler @Inject()(env: Environment, config: Configuration, sourceMapper: OptionalSourceMapper, router: Provider[Router])
  extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

    override def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
      Future.successful(
        Status(statusCode)("A Client error occurred: " + message)
      )
    }

    override def onServerError(request: RequestHeader, exception: Throwable) = {
      Future.successful(
        InternalServerError("A server error occurred:" + exception.getMessage)
      )
    }

    override def onProdServerError(request: RequestHeader, exception: UsefulException) = {
      Future.successful(
        InternalServerError("A server error occurred: " + exception.getMessage)
      )
    }

    override def onForbidden(request: RequestHeader, message: String) = {
      Future.successful(
        Forbidden("You are not allowed to access this resources.")
      )
    }

  }
