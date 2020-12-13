package com.finatra.movies.di

import java.net.URI
import java.util.concurrent.Executors

import javax.inject.{Inject, Singleton}
import com.google.inject.Provides
import com.twitter.inject.{Injector, TwitterModule}
import com.db.service._
import com.db.service.impl.DbMoviesService
import com.db.service.impl.di.DbExecutionContext
import com.twitter.finagle.http.filter.Cors
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext


class CorsFilter @Inject()(policy: Cors.Policy) extends Cors.HttpFilter(policy)


object ServiceModule extends TwitterModule {
  protected override def configure(): Unit = {
    super.configure()

    bind[MovieService].to[DbMoviesService]
  }


  override def singletonShutdown(injector: Injector): Unit = {
    super.singletonShutdown(injector)

    injector.instance[Database].close()
  }

  @Provides
  @DbExecutionContext
  @Singleton
  def providesDbExecutionContext(): ExecutionContext = {
    ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())
  }

  @Provides
  @Singleton
  def providesDatabase(): Database = {
    Database.forConfig("assignmentDb")
  }

  @Singleton
  @Provides
  def providesCorsPolicy(): Cors.Policy = {
    Cors.Policy(
      origin=> {
        Some(origin)
      },
      method => {
        Some(Seq("GET", "HEAD", "POST", "DELETE", "OPTIONS", "PUT", "PATCH"))
      },
      headers => {
        Some(Seq("Accept", "Content-Type", "Authorization"))
      }
    )
  }
}
