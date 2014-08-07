package de.sgeorgi.socket_sqrt

import java.net.InetSocketAddress

import akka.actor._
import akka.io.Tcp.{PeerClosed, Received, Write}
import akka.io.{IO, Tcp}
import akka.util.ByteString

/**
 * Created by sgeorgi on 07.08.14.
 */
class SocketServer(port: Int) extends Actor {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", port))

  def receive = {
    case b@Bound(localAddress) =>
      println("de.sgeorgi.socket_sqrt.SocketServer started")

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val handler = context.actorOf(Props[SocketHandler])
      val connection = sender()
      connection ! Register(handler)
      println("Client registered")
      sender() ! Write(ByteString("Type in 0 to exit, or any other Integer to get the square root for!\n"))
  }
}

class SocketHandler extends Actor {
  def receive = {
    case Received(data) => {
      val number = data.decodeString("utf8").stripLineEnd.toInt

      number match {
        case 0 => {
          println("Client wished to disconnect")
          context stop self
        }
        case _: Int => sender() ! Write(ByteString("Square root of " + number + " is " + Sqrt(number).toString + "\n\n"))
      }
    }
    case PeerClosed => context stop self
  }
}
