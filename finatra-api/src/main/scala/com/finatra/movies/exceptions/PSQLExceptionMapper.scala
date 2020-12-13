package com.finatra.movies.exceptions

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.inject.Logging
import javax.inject.Inject
import org.postgresql.util.PSQLException

class PSQLExceptionMapper @Inject()(response: ResponseBuilder) extends ExceptionMapper[PSQLException] with Logging {

  val integrityConstraintViolation: String = "23"

  override def toResponse(request: Request, throwable: PSQLException): Response = {
    val code = throwable.getServerErrorMessage.getSQLState

    if (code.startsWith(integrityConstraintViolation)) {
      response.badRequest(throwable.getServerErrorMessage.toString)
    } else {
      error("Something went wrong", throwable)
      response.internalServerError("Something went wrong")
    }
  }
}
