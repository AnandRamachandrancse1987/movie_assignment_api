package com.finatra.movies

import com.finatra.movies.di.{CorsFilter, JacksonModule, ServiceModule}
import com.finatra.movies.endpoint.{MovieController}
import com.finatra.movies.exceptions.PSQLExceptionMapper
import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

class Server extends HttpServer {

  override protected def modules: Seq[Module] = Seq(ServiceModule)


  override protected def jacksonModule: Module = JacksonModule

  override protected def defaultFinatraHttpPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CorsFilter]
      .filter[CommonFilters]
      .add[MovieController]
      .exceptionMapper[PSQLExceptionMapper]
  }
}

object ServerMain extends Server