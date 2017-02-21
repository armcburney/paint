/**
  * ViewSpec.scala
  *
  * @author: Andrew McBurney
  * @note:   Unit testing file with specs2
  */

package ca.andrewmcburney.cs349.a2

import View._
import org.specs2.mutable.Specification

class ViewSpec extends Specification {
  "View" should {
    "true == true" in {
      true mustEqual true
    }
  }
}

