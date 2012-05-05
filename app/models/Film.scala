package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Film(
  id: Long = 0, 
  title: String = "unknown film"
)

object Film {
  
  // -- Parsers
  
  /**
   * Parse a Computer from a ResultSet
   */
  val simple = {
    get[Long]("film.id") ~
    get[String]("film.name") map {
      case id~name => Film(id, name)
    }
  }
  
  
}

