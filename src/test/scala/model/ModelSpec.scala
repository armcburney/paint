/**
  * ModelSpec.scala
  *
  * @author: Andrew McBurney
  * @note:   Unit testing file with specs2
  */

package ca.andrewmcburney.cs349.a2

import Model._
import org.specs2.mutable.Specification

class ModelSpec extends Specification {
  "Model" should {
    "true == true" in {
      true mustEqual true
    }
  }
}

