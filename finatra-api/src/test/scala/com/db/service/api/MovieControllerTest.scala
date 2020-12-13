package com.db.service.api

import com.google.inject.Module
import com.finatra.movies.Server
import com.finatra.movies.di.ServiceModule
import com.finatra.movies.endpoint.{Movie, MovieWithRealation}
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest


class MovieControllerTest extends FeatureTest with TestData {

  protected lazy val mdServer = new Server() {
    override protected def modules: Seq[Module] = Seq(ServiceModule)
  }

  protected lazy val server = new EmbeddedHttpServer(
    twitterServer = mdServer
  )

  test("/api/search/:search") {
      val url = s"/api/search/Toy"
      server.httpGetJson[Seq[Movie]](url, andExpect = Status.Ok)
  }

  test("/api/getMovie/:query") {
    val url = s"/api/getMovie/Toy%20Story%20(1995)"
    server.httpGetJson[MovieWithRealation](url, andExpect = Status.Ok)
  }
}
