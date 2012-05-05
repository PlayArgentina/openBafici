package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS

import models.Film

import utils.Bafici

object Films extends Controller {
  
  def list = Action {
    Ok(views.html.list())
  }

  def show(id: String) = Action {
    val film = Film(1, "La banda del openBafici")
    Ok(views.html.show(film))
  }

  def test() = Action {
    var query = Bafici.endpoint + """_search?pretty=1&q=synopsis_es:'buenos aires'"""
    //Ok(views.html.test(query))
    Async {
      WS.url("http://zenithsistemas.com:9200/").get().map { response =>
        Ok(
          views.html.test(
            (response.text).as[String]
          )
        )
      }
    }
  }

}