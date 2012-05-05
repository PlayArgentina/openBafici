package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Film(
  id: Long = 0, 
  title: String = "unknown film",
  title_es: String = "desconocido",
  url_ticket: String = "http://google.com",
  year: Long = 1984,
  generes_list: String = "ninguno genero",
  cast: String = "Van Damme",
  id_youtube: String = "werwerwer",
  filepic1: String = "image.png",
  prodteam: String = "Scorcese",
  synopsis_es: String = "sinopsis",
  synopsis_en: String = "sinopsis",
  duration: Long = 95,
  director: String = "Fulano",
  updated_ts: String = "20-20-1945"
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

