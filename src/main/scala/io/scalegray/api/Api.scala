package io.scalegray


import scalaz._
//import Scalaz._
//import scalaz.Validation
import scalaz.Validation.FlatMap._
import scalaz.NonEmptyList._

import com.twitter.server.TwitterServer
import com.twitter.finagle.Http
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import java.util.UUID



object SystemCheck {

  def ping(): String = {
    //needs to be a lot better than this
      return "I'm Up!"
     }

}

object Main extends TwitterServer {

//To check whether the system is up and doing just fine.
   val systemUp: Endpoint[String] = get("ping") {
     Ok(SystemCheck.ping())
   }

//1 Save node endpoint - this saves the node details that comes from the UI into the nodes bucket with validation = false


//2. bootstrap - this endpoint is called by the sg agent from the node. this will contain all the data of the node table
//so no need to use the incomplete decoder.
val patchedNode: Endpoint[Node] = body.as[Node]
   val bootstrapNode: Endpoint[Node] = post("bootstrap" :: patchedNode) { (t: Node) =>
      //validate Node
      (Node.checkId(t.uid) leftMap {err: NonEmptyList[Throwable] => err }).flatMap {id: Option[Long] =>
          println("000000000000000000000000000")
           println(id.getOrElse(""))
           //id match {
          //
          //
         // }
         }
    //  for {
    //  id <- Node.checkId(t.uid) //check if id exists, the return id else throw exception
    //  v <- Node.validateId(id) //send an option and see if its there or nots
    //  y <- subscribeQsystem(id) //call the QSystem actor
    //  } yield {
      //   id
  //  }
  //      println(t.uid)
    //  Node.getById(t.uid)
    //
      //return a res

    //     println(t)
      Ok(t)
   } handle {
    case e: Exception => BadRequest(e)
  }


//3.


   val api: Service[Request, Response] = (systemUp :+: bootstrapNode).toService


   def main(): Unit = {

     val server =  Http.server
         .serve(":8090", api)
         onExit { server.close() }

       Await.ready(adminHttpServer)
   }
}
