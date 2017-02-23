/**
  * MainSpec.scala
  *
  * @author: Andrew McBurney
  * @note:   Unit testing file with specs2
  */

package ca.andrewmcburney.cs349.a2

import Main._
import org.specs2.mutable.Specification

class MainSpec extends Specification with Mockito {
  "Main" should {
    "true == true" in {
      true mustEqual true
    }
  }
}

