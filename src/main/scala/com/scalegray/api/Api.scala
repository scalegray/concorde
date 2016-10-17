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
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import scala.concurrent.Future




object SystemCheck {

  def ping(): String = {
    //needs to be a lot better than this
    return "I'm Up!"
  }
}

class ApiServer(implicit system: ActorSystem, supervisor: ActorRef) extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

   private def subscribeQsystem(id: Long): ValidationNel[Throwable, Long] = {

     println(supervisor)
     //call supervisor actor and create a QActor to subscribe to the queue.

    val list = List(1,2, 3)
    return Validation.success[Throwable, Long](12312335343434L).toValidationNel
  }

  val myRoutes = {nodeRoutes ~ userRoutes ~ bootstrap}

  def startApiServer() {
      Http().bindAndHandle(myRoutes, "localhost", 8090)
 }

  def nodeRoutes: Route = pathPrefix("nodes") {
    implicit val nodeJson = jsonFormat4(Node.apply)

      get {
          complete("getting node")
        } ~
        post {   //TODO: authentication mechanisms and akka-http
            entity(as[Node]) { node =>      //try using akkahttp-circe for better performance
              val saved: Future[String] = node.saveToDB()
              onComplete(saved) { saved =>
                println(saved)
                complete("Node created")
              }
            }
        }
    }

    def bootstrap: Route =  pathPrefix("bootstrap" / LongNumber) { uid =>
        //implicit val bootstrapJson = jsonFormat1(Bootstrap.apply)
        put {
             println(uid)
             for {
               node <- Node.checkId(uid)

             }
             complete("Bootstrapped")
//validate UID - check if UID exists in the system
//change UID validated = true
//notify the QSystem Actor
//bootstrap complete

        }
    }

  def userRoutes: Route = pathPrefix("users") {
    implicit val nodeJson = jsonFormat4(User.apply)

      get {
         complete("getting users")
      } ~
      post {
          entity(as[User]) { user =>  //http://doc.akka.io/docs/akka/2.4/scala/http/routing-dsl/directives/marshalling-directives/entity.html
            val saved: Future[String] = user.saveToDB()
            onComplete(saved) { saved =>
              println(saved)
              complete("Node created")
          }
        }
     }
  }

}

//Akka design:
// When the /bootstrap is called -
//1. pull the relevant node with UID
//2. Notify Qsystem actor about 'bootstrap' authentication
//3. Qsystem will spin off an subQ actor which subscribe to the queue. [QSystem will supervise subQ actors]

//Bounded context - model bootstrap and node with differnet contexts under same roof.
//http://doc.akka.io/docs/akka/2.4/scala/http/routing-dsl/directives/path-directives/index.html#pathdirectives
//unmarshallers - http://malaw.ski/2016/04/10/hakk-the-planet-implementing-akka-http-marshallers/
