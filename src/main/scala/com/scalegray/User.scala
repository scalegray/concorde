package com.scalegray



import scalaz._
import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import io.jvm.uuid._
import scala.concurrent.{ExecutionContextExecutor, Future}

import scala.concurrent.ExecutionContext.Implicits.global


case class User(firstname: String, secondname: String, email: String, password: String) {
  def saveToDB(): Future[String] = Future {
     //scylladb save
     println(firstname)

    "saved to db successfully"
  }
}
