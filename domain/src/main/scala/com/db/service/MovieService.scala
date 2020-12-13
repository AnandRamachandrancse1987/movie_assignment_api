package com.db.service

import scala.concurrent.Future

trait MovieService {

  def searchMovie(name: String): Future[Seq[(Int,String,String)]]

  def fetchMovieByName(name: String): Future[Seq[(Int,String,String)]]

  def fetchLinkByMovieId(id: Int): Future[Option[(Long,Long)]]

  def fetchAverageRatingByMovieId(id: Int): Future[Option[Float]]

  def fetchTagByMovieId(id: Int): Future[Seq[String]]
}
