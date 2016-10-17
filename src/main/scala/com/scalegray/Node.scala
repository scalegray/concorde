package com.scalegray


import scalaz._
import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import io.jvm.uuid._
import scala.concurrent.{ExecutionContextExecutor, Future}

import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/*
 * A node represents a single entity that can be a virtual machine, a docker container or an IoT device.
 */

case class Node(name: String, uid: Long, host: String, validated: Boolean) { //Extend this to support more information and hold Cloud node data
  def saveToDB(): Future[String] = Future {
     //scylladb save
     println(name)
     println(uid)
     println(host)
     println(validated)
    "saved to db successfully"
  }
}

object Node {
/*
 def checkId(id: Long): ValidationNel[Throwable, Option[Node]] = {
   //do a call to db class and fetch an Option[Node]
      return Validation.success[Throwable, Option[Node]](some(new Node(UID.getUID
      match {case Success(s) => s}))
    ).toValidationNel
 } */

}
trait NodeJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val nodeJson = jsonFormat4(Node.apply)
//  implicit val userJson = jsonFormat4(User.apply)
}
