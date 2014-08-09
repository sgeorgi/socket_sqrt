package de.sgeorgi.socket_sqrt


/**
 * Created by sgeorgi on 07.08.14.
 */

object Sqrt {

  def findSqrt(x: Int): Double = {
    def goodEnough(guess: Double, x: Double) = scala.math.abs((guess * guess) - x) < 0.001

    def improveGuess(guess: Double, x: Double) = (x / guess + guess) / 2

    def findSqrtIter(guess: Double, x: Int): Double = {
      if (goodEnough(guess, x)) guess
      else findSqrtIter(improveGuess(guess, x), x)
    }

    def roundAt(p: Int)(n: Double): Double = { val s = math pow (10, p); (math round n * s) / s }
    def roundAt2(n: Double) = roundAt(2)(n)

    roundAt2(findSqrtIter(1.0, x))
  }

  def apply(x: Int): Double = findSqrt(x)

}
