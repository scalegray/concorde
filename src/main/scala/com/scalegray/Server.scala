package com.scalegray

import com.twitter.finagle.Http
import com.twitter.util.Await
import akka.actor.Props
import akka.actor.{ ActorSystem, ActorRef, Actor }

object ConcordeEngine {

  def main(args: Array[String]) {
    //start the actor system and supervisor actor

 def start(aSystem: Boolean => ActorSystem) { //Bool will be replaced with Config class(ConfigFactory)

   val system = aSystem(true)
   val supervisor = system.actorOf(Props(classOf[SupervisorActor]), "supervisor-actor")

   new Api(system, supervisor).startApiServer() //API server is started
  }
  def newActorSystem(name: String)(config: Boolean): ActorSystem = {
      ActorSystem(name)
  }
    start(newActorSystem("concorde")(_)) //currying
   }
}
