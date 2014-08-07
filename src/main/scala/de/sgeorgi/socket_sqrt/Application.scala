package de.sgeorgi.socket_sqrt

import akka.actor.{ActorSystem, Props}

/**
  * Created by sgeorgi on 07.08.14.
  */
object Application {
   def main(args: Array[String]): Unit = {
     println("Starting up ActorSystem")
     val port = 8000
     ActorSystem().actorOf(Props(new SocketServer(port)))
   }

 }
