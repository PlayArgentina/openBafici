package utils

import play.Play._

object Bafici {
  val endpoint = application.configuration.getString("bafici.endpoint") match { 
    case null => "" case s: String => s 
  }

}
