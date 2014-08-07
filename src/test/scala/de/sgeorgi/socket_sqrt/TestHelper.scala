package de.sgeorgi.socket_sqrt

/**
 * Created by sgeorgi on 07.08.14.
 */

import akka.testkit.TestKit
import org.scalamock.scalatest.MockFactory
import org.scalatest._

trait StopSystemAfterAll extends BeforeAndAfterAll {
  this: TestKit with Suite =>
  override protected def afterAll(): Unit = {
    super.afterAll()
    system.shutdown()
  }

}

trait UnitSpec extends FunSpec with Matchers with MockFactory


