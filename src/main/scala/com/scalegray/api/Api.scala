package com.scalegray

import scalaz._
//import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import scalaz.NonEmptyList._
import com.twitter.server.TwitterServer

import com.twitter.finagle.{ Http, Service }
import com.twitter.finagle.http.{ Request, Response }

import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import java.util.UUID

import akka.actor.ActorSystem
import akka.actor.ActorRef

object SystemCheck {

  def ping(): String = {
    //needs to be a lot better than this
    return "I'm Up!"
  }
}

class Api(system: ActorSystem, supervisor: ActorRef) extends TwitterServer {


   private def subscribeQsystem(id: Long): ValidationNel[Throwable, Long] = {

     println(supervisor)
     //call supervisor actor and create a QActor to subscribe to the queue.

    val list = List(1,2, 3)
    return Validation.success[Throwable, Long](12312335343434L).toValidationNel
  }

  //To check whether the system is up and doing just fine.
  val systemUp: Endpoint[String] = get("ping") {
    Ok(SystemCheck.ping())
  }
  //1. users post
//  val usersCreate: Endpoint[String] = post("users")

   //2. users get /users/:email
   //val usersGet: Endpoint[String] = get()

   //3. users put /users/:email
//   val usersPut: Endpoint[String] = put()

  //4. Save node endpoint - this saves the node details that comes from the UI into the nodes bucket with validation = false
  // UID is created here and returned back. a Map of host -> UID is retuned back to the UI

  //5. bootstrap - this endpoint is called by the sg agent from the node. this will contain all the data of the node table
  //so no need to use the incomplete decoder.
  val patchedNode: Endpoint[Node] = body.as[Node]
  val bootstrapNode: Endpoint[Node] = post("bootstrap" :: patchedNode) { (t: Node) =>
    //validate Node

    for {
      id <- Node.checkId(t.uid) leftMap { err: NonEmptyList[Throwable] => err } //check if id exists, the return id else throw exception
      //  v <- Node.validateId(id) //send an option and see if its there or nots
      y <- subscribeQsystem(83437873847387434L) //call the supervisor aka Qsystem actor
    } yield {
      id
    }
    //     println(t)
    val r = new Node(83437873847387434L)
    Ok(r)
     } handle {
     case e: Exception => BadRequest(e)
  }

  val api: Service[Request, Response] = (systemUp :+: bootstrapNode).toService

  def startApiServer() {

    val server = Http.server
      .serve(":8090", api)
    onExit { server.close() }
    Await.ready(server)
  }

}
