package com.finatra.movies.endpoint

import java.net.{URLDecoder, URLEncoder}

import com.db.service.MovieService
import com.twitter.finagle.http.{Request, Status}
import com.twitter.finagle.http.filter.Cors
import javax.inject.Inject
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.exceptions.HttpException
import com.twitter.finatra.request.RouteParam

import scala.concurrent.Await
import scala.concurrent.duration._

class MovieController @Inject()(movieService: MovieService) extends Controller {

  options("/api/:*") {
    _: Request => response.ok
  }

  get("/api/search/:query") { request: GetPostRequest =>
    Await.result(movieService.searchMovie(request.query),30.seconds).map {
      movie => {
        Movie(movie._1,movie._2,movie._3)
      }
    }
  }

  get("/api/getMovie/:query") { request: GetPostRequest =>

    val keyword = URLDecoder.decode(request.query, "UTF-8")
    val movie:Option[(Int,String,String)] = Await.result(movieService.fetchMovieByName(keyword),30.seconds).headOption
    if(movie.isDefined) {
      val links:Option[(Long,Long)]  = Await.result(movieService.fetchLinkByMovieId(movie.get._1),30.seconds)
      val tags:Seq[String] = Await.result(movieService.fetchTagByMovieId(movie.get._1),30.seconds)
      val ratings:Option[Float] = Await.result(movieService.fetchAverageRatingByMovieId(movie.get._1),30.seconds)

      MovieWithRealation(Movie(movie.get._1,movie.get._2,movie.get._3),links.get._1,links.get._2,ratings.getOrElse(0),tags)
    } else {
      throw HttpException(Status.NotFound, "Movie not found")
    }
  }
}

case class Movie(movieid:Int,title:String,genres:String)

case class MovieWithRealation(movie:Movie,imdbLink:Long,tmdbLink:Long,avgRatings:Float,tags:Seq[String])

case class GetPostRequest(@RouteParam query: String)






