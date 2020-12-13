package com.db.service.impl

import com.db.service.impl.di.DbExecutionContext
import javax.inject.{Inject, Singleton}
import slick.jdbc.PostgresProfile.api._
import com.db.service.MovieService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DbMoviesService @Inject()(db: Database,
                                @DbExecutionContext implicit val executionContext: ExecutionContext) extends MovieService with DbHelper {

  override def searchMovie(query: String): Future[Seq[(Int,String,String)]] = {
    val search = "%" + query + "%"
    db.run(
      sql"""SELECT movieid,title,genres from movies where title like ${search}""".as[(Int,String,String)]
    )
  }

  override def fetchMovieByName(name: String): Future[Seq[(Int,String,String)]] = {
    db.run(
      sql"""SELECT movieid,title,genres from movies where title = ${name}""".as[(Int,String,String)]
    )
  }

  override def fetchLinkByMovieId(id: Int): Future[Option[(Long,Long)]] = {
    db.run(
      sql"""SELECT imdbid,tmdbid from links where movieid = ${id}""".as[(Long,Long)].headOption
    )
  }

  override def fetchAverageRatingByMovieId(id: Int): Future[Option[Float]] = {
    db.run(
      sql"""select ROUND(Avg(rating)) from ratings where movieid= ${id}""".as[Float].headOption
    )
  }

  override def fetchTagByMovieId(id: Int): Future[Seq[String]] = {
    db.run(
      sql"""SELECT tag from tags where movieid = ${id} group by tag""".as[String]
    )
  }

}
