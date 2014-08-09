package de.sgeorgi.socket_sqrt

import java.net.InetSocketAddress

import akka.actor._
import akka.io.Tcp.{PeerClosed, Received, Write}
import akka.io.{IO, Tcp}
import akka.util.ByteString

/**
 * Created by sgeorgi on 07.08.14.
 */

/**
 *  Trait to contain immplicit conversions regarding usage of ByteString
 */
trait Implicits {

  implicit def string2ByteString(s: String): ByteString = ByteString(s)

  implicit def ByteString2String(bs: ByteString): String = bs.decodeString("UTF-8").stripLineEnd

}

/**
 *  Main Actor to listen on the Socket. Spawns another Actor upon connection
 */
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
      sender() ! Write("Type in 'exit' to exit, or any other Integer to get the square root for!\n")
  }
}

/**
 * Main Socket Handler to handle incoming ByteStrings (things typed on a telnet session for example)
 */
class SocketHandler extends Actor with Implicits {

  def receive = {
    case Received(data) =>
      parseInput(data) match {
        case "exit" =>
          println("Client disconnected")
          context stop self
        case n: Int => sender() ! Write("Square root of " + n + " is " + Sqrt(n).toString + "\n")
        case s: String => sender() ! Write("Could not parse string '" + s + "' (type 'exit' to quit) \n")
      }
    case PeerClosed => context stop self
  }

  /**
   * Parses a String and checks if it can be converted to an Int, otherwise returns raw input
   * Takes an implicit String, since the ByteString input can be implicitly converted
   */
  private def parseInput(implicit s: String) = s match {
    case _ if s.matches("[+-]?\\d+") => scala.math.abs(Integer.parseInt(s))
    case _ => s
  }

}

