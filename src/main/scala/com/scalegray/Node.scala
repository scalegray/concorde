package com.scalegray


import scalaz._
import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import io.jvm.uuid._
import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext.Implicits.global




case class Node(uid: Long)

object Node {

 def checkId(id: Long): ValidationNel[Throwable, Option[Node]] = {
   //do a call to db class and fetch an Option[Node]
      return Validation.success[Throwable, Option[Node]](some(new Node(UID.getUID
      match {case Success(s) => s}))
    ).toValidationNel
 }
 def saveToDB(): Future[String] = Future {
     println("saved to db successfully")
     "test"
 }
}

object NodeJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val nodeJson = jsonFormat1(Node.apply)
}
