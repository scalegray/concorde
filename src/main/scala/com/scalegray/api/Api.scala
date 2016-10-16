package com.scalegray

import scalaz._
//import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import scalaz.NonEmptyList._

//import com.twitter.server.TwitterServer

//import com.twitter.finagle.{ Http, Service }
//import com.twitter.finagle.http.{ Request, Response }

import com.twitter.util.Await
//import io.finch._
//import io.finch.circe._
//import io.circe.generic.auto._
import java.util.UUID

import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.concurrent.Future



object SystemCheck {

  def ping(): String = {
    //needs to be a lot better than this
    return "I'm Up!"
  }
}

class ApiServer(implicit system: ActorSystem, supervisor: ActorRef) {

  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


   private def subscribeQsystem(id: Long): ValidationNel[Throwable, Long] = {

     println(supervisor)
     //call supervisor actor and create a QActor to subscribe to the queue.

    val list = List(1,2, 3)
    return Validation.success[Throwable, Long](12312335343434L).toValidationNel
  }

  val routes: Route =
       get {
         pathPrefix("nodes") {
           // there might be no item for a given id
          // val maybeItem: Future[Option[Item]] = fetchItem(id)
          println("booyah")
          val item = "boo"
          complete(item)
           /*onSuccess(maybeItem) {
             case Some(item) => complete(item)
             case None       => complete(StatusCodes.NotFound)
           } */
         }
       }
         post {
           path("nodes") {
             entity(as[Node]) { node =>  //http://doc.akka.io/docs/akka/2.4/scala/http/routing-dsl/directives/marshalling-directives/entity.html
               val saved: Future[String] = node.saveToDB()
               onComplete(saved) { s =>
                 println(s)
                 complete("Node created")
               }
             }
           }
         }

  def startApiServer() {
      Http().bindAndHandle(routes, "localhost", 8090)
 }

}
