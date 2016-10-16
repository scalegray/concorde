package com.scalegray

import com.twitter.finagle.Http
import com.twitter.util.Await
import akka.actor.Props
import akka.actor.{ ActorSystem, ActorRef, Actor }

object ConcordeEngine {

  def main(args: Array[String]) {
    //start the actor system and supervisor actor

 def start(aSystem: Boolean => ActorSystem) { //Bool will be replaced with Config class(ConfigFactory)

   implicit val system = aSystem(true)
   implicit val supervisor = system.actorOf(Props(classOf[SupervisorActor]), "supervisor-actor")
   new ApiServer().startApiServer()
 }

  def newActorSystem(name: String)(config: Boolean): ActorSystem = {
      ActorSystem(name)
  }
  start(newActorSystem("concorde")(_)) //currying
 }
}
