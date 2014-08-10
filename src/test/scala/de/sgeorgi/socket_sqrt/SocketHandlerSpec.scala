package de.sgeorgi.socket_sqrt

import akka.actor.{Props, ActorSystem}
import akka.io.Tcp.Write
import akka.testkit.{TestKit, DefaultTimeout, TestActorRef}
import akka.util.ByteString
import org.scalatest.{FunSpecLike, FunSpec, Matchers, BeforeAndAfterAll}

/**
 * Created by sgeorgi on 09.08.14.
 */

//class SocketHandlerSpec extends TestKit(ActorSystem("SocketHandlerSpec")) with fixture.FunSpec with ImplicitSender with  DefaultTimeout with Matchers with BeforeAndAfterAll {
class SocketHandlerSpec(_system: ActorSystem) extends TestKit(_system) with FunSpecLike with Matchers with BeforeAndAfterAll  {

  def this() = this(ActorSystem("testsystem"))
  val actorRef = system.actorOf(Props(new SocketHandler))

  override def afterAll() {
    system.shutdown()
  }

  describe("SocketHandler") {
    describe("receive[Int]") {
      it("returns 5.00 when received a ByteString('25')") {
        val props = Props(new SocketHandler)
        val handler = system.actorOf(props, "handler")
        handler ! Write(ByteString("25"))
        expectMsg(Write)
      }
    }

  }
}