package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import play.api.libs.json.Json
import models.Film


object FilmsElastic extends Controller {

  def home() = Action {
    Ok(views.html.index())
  }

  def list(page: Long = 1, sort: String = "", filter: String = "") = Action {

    val films = Seq(
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-262").get,
      Film.findById("b6f980d6-5070-48b7-aeea-41d945b34175-96").get,
      Film.findById("b6f980d6-5070-48b7-aeea-41d945b34175-130").get,
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-33").get,
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-38").get,
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-40").get,
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-45").get,
      Film.findById("2732fbf4-4e55-4794-8e98-e5d5fa6a0419-64").get,
      Film.findById("b6f980d6-5070-48b7-aeea-41d945b34175-113").get
    )

    Ok(views.html.list(
      page = 1, sort = "", filter = "", films = films
    ))
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
    Ok(views.html.index())
  }

}
