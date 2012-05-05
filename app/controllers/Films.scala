package controllers

import play.api._
import play.api.mvc._

import models.Film

object Films extends Controller {

  def home() = Action {
    Ok(views.html.index())
  }

  def list(page: Long = 1, sort: String = "", filter: String = "") = Action {

    //TODO query

    Ok(views.html.list(page, sort, filter, Seq[Film]()))
  }

  def show(id: String) = Action {
    val film = Film(1, "La banda del openBafici")
    Ok(views.html.show(film))
  }

}
