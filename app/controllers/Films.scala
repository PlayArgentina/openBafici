package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import play.api.libs.json.Json

import models.Film

object Films extends Controller {
  
  def list = Action {

    val films = Seq(
      Film("1", "La banda del openBafici"),
      Film("2", "La banda del openBafici II")
    )

    Ok(views.html.list(films = films))
  }

  def show(id: String) = Action {
    //2732fbf4-4e55-4794-8e98-e5d5fa6a0419-4
    val film = Film.findById(id)
    film.map { film =>
      Ok(views.html.show(film))  
    }.getOrElse {
      NotFound
    }

  }

  def test() = Action {
    var query = Film.endpoint + """_search?q=synopsis_es:'buenos aires'"""

    query = "http://zenithsistemas.com:9200/gcba/bafici/2732fbf4-4e55-4794-8e98-e5d5fa6a0419-4?fields=id,title,title_es,url_ticket,year,generes_list,cast,id_youtube,filepic1,prodteam,synopsis_es,synopsis_en,duration,director,updated_ts"

    Async {
      WS.url(query).get().map { response =>
        val json = Json.parse(response.body)
        val pic = (json \ "fields" \ "filepic1").as[String]
        Ok(views.html.test(query, pic))
      }
    }


  }

}