

package org.scalegray

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Main extends App {

  //creating the actor system
  implicit val system = ActorSystem("scalegray")

  val service = system.actorOf(Props[ServiceActor], "http-service")

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}
