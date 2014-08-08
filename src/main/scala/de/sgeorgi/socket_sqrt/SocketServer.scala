package de.sgeorgi.socket_sqrt

import java.net.InetSocketAddress

import akka.actor._
import akka.io.Tcp.{PeerClosed, Received, Write}
import akka.io.{IO, Tcp}
import akka.util.ByteString

/**
 * Created by sgeorgi on 07.08.14.
 */
trait Implicits {
  implicit def string2ByteString(s: String): ByteString = ByteString(s)
}

class SocketServer(port: Int) extends Actor with Implicits {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", port))

  def receive = {
    case b@Bound(localAddress) =>
      println("SocketServer started")

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val handler = context.actorOf(Props[SocketHandler])
      val connection = sender()
      connection ! Register(handler)
      println("Client registered")
      sender() ! Write("Type in 0 to exit, or any other Integer to get the square root for!\n")
  }
}

class SocketHandler extends Actor with Implicits {
  def receive = {
    case Received(data) =>
      val number = data.decodeString("utf8").stripLineEnd.toInt

      number match {
        case 0 =>
          println("Client disconnected")
          context stop self
        case _: Int => sender() ! Write("Square root of " + number + " is " + Sqrt(number).toString + "\n")
      }
    case PeerClosed => context stop self
  }
}

