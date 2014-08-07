package de.sgeorgi.socket_sqrt

/**
 * Created by sgeorgi on 07.08.14.
 */
class SqrtSpec extends UnitSpec {
  describe("Sqrt Object") {
    describe(".findSqrt") {
      it("returns 2.0 as the sqrt of 4") {
        assert(Sqrt.findSqrt(4) == 2.0)
      }
      it("returns 5.0 as the sqrt of 25") {
        assert(Sqrt.findSqrt(25) == 5.0)
      }
    }
  }
}
