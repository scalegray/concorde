package com.scalegray

import com.twitter.finagle.Http
import com.twitter.finagle.{ Http, Service }
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.util.Await
import akka.actor.Props
import akka.actor.{ ActorSystem, ActorRef, Actor }

object ConcordeEngine {

  def main(args: Array[String]) {
    //start the actor system and supervisor actor
    val system = ActorSystem("concorde")
    val supervisor = system.actorOf(Props(classOf[SupervisorActor]), "supervisor-actor")

    new Api(system, supervisor).startApiServer() //API server is started
  }

}
