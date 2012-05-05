package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS

import models.Film

import utils.Bafici

object Films extends Controller {
  
  def list = Action {

    val films = Seq(
      Film(1, "La banda del openBafici"),
      Film(2, "La banda del openBafici II")
    )

    Ok(views.html.list(films = films))
  }

  def show(id: String) = Action {
    val film = Film(1, "La banda del openBafici")
    Ok(views.html.show(film))
  }

  def test() = Action {
    var query = Bafici.endpoint + """_search?q=synopsis_es:'buenos aires'"""

    query = "http://zenithsistemas.com:9200/"

    query = "http://zenithsistemas.com:9200/gcba/bafici/2732fbf4-4e55-4794-8e98-e5d5fa6a0419-4"

    query = "http://zenithsistemas.com:9200/gcba/bafici/2732fbf4-4e55-4794-8e98-e5d5fa6a0419-4?fields=id,title,title_es,url_ticket,year,generes_list,cast,id_youtube,filepic1,prodteam,synopsis_es,synopsis_en,duration,director,updated_ts"

    //Ok(views.html.test(query,"body"))

    Async {
      val ws = WS.url(query)
      val wsGet = ws.get()

      WS.url(query).get().map { response =>

        val body = response.body

        //val json = response.json

        //val title = (json).as[String]

        Ok(
          views.html.test(query, body)
        )
      }
    }


  }

}