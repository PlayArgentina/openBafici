package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import play.Play.application
import play.api.libs.ws.WS
import play.api.libs.json.{Json, JsValue}

case class Film(
  id: String            = "",
  title: String         = "unknown film",
  title_es: String      = "desconocido",
  url_ticket: String    = "http://google.com",
  year: Long            = 1984,
  generes_list: String  = "ninguno genero",
  cast: String          = "Van Damme",
  id_youtube: String    = "werwerwer",
  filepic1: String      = "image.png",
  prodteam: String      = "Scorcese",
  synopsis_es: String   = "sinopsis",
  synopsis_en: String   = "sinopsis",
  duration: Long        = 95,
  director: String      = "Fulano",
  updated_ts: String    = "20-20-1945"
)

object Film {
  
 val fields: String = 
    "id,title,title_es,url_ticket,year,generes_list,cast,id_youtube,filepic1," +
    "prodteam,synopsis_es,synopsis_en,duration,director,updated_ts"

  val endpoint = application.configuration.getString("bafici.endpoint") match { 
    case null => "" case s: String => s 
  }

  def apply(json: JsValue): Film = {

    val fields = json \ "fields"
    
    Film(
      id                  = readJson(json, "_id", ""),
      title               = readJson(fields, "title", "unknown film"),
      title_es            = readJson(fields, "title_es", "desconocido"),
      url_ticket          = readJson(fields, "url_ticket", "http://google.com"),
      year                = readJsonLong(fields, "year", 1984),
      generes_list        = readJson(fields, "generes_list", "ninguno genero"),
      cast                = readJson(fields, "cast", ""),
      id_youtube          = readJson(fields, "id_youtube", ""),
      filepic1            = readJson(fields, "filepic1", ""),
      prodteam            = readJson(fields, "prodteam", ""),
      synopsis_es         = readJson(fields, "synopsis_es", ""),
      synopsis_en         = readJson(fields, "synopsis_en", ""),
      duration            = readJsonLong(fields, "duration", 90),
      director            = readJson(fields, "director", ""),
      updated_ts          = readJson(fields, "updated_ts", "")
    )

  }

  private def readJson(node: JsValue, field: String, default: String): String = {
    (node \ field).asOpt[String].map { value =>
      value
    }.getOrElse {
      default
    }
  }

  private def readJsonLong(node: JsValue, field: String, default: Long): Long = {
    (node \ field).asOpt[Long].map { value =>
      value
    }.getOrElse {
      default
    }
  }

  // -- Parsers
  
  /**
   * Parse a Film from a json element
   */
  val simple = {
    get[String]("film.id") ~
    get[String]("film.name") map {
      case id~name => Film(id, name)
    }
  }

  def findById(id: String): Option[Film] = {
    val query = endpoint + id + "?fields=" + fields

    val response = WS.url(query).get().await.get

    val json = Json.parse(response.body)
    Option[Film](Film(json))

  }

}

