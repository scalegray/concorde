package com.scalegray

import akka.actor.Actor


/*
 * Supervisor actor - Creates child QueueActor and subscribe to a queue to stream metrics data
 * when a metric agent calls the API
 */
class SupervisorActor extends Actor {

  def receive() = {
    case _ => println("test")
  }
}
