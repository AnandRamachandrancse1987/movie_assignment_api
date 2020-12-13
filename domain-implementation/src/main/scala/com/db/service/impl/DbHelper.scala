package com.db.service.impl

trait DbHelper {

  def updateCodeToOptionalResult[T](result: => T)(code: Int): Option[T] = {
    if (code > 0) {
      Some(result)
    } else {
      None
    }
  }
}
