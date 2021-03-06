package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import play.api.libs.json.Json

import models.Film

object Films extends Controller {

  val FILMS_PAGE_LEN = 10

  def home() = Action {
    Ok(views.html.index())
  }

  def list(page: Long = 1, sort: String = "", filter: String = "") = Action {

    val films = Film.findAll(page = page, size = FILMS_PAGE_LEN)

    Ok(views.html.list(page, sort, filter, films))
  }

  def show(id: String) = Action {
    //2732fbf4-4e55-4794-8e98-e5d5fa6a0419-4
    val film = Film.findById(id)
    film.map { film: Film =>
      Ok(views.html.show(film))
    }.getOrElse {
      NotFound
    }

  }

  def test() = Action {

    val films = Film.findAll()

    Ok(views.html.test(films))
  }

}
