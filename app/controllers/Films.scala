package controllers

import play.api._
import play.api.mvc._

import models.Film

object Films extends Controller {
  
  def list = Action {
    TODO
  }

  def show(id: String) = Action {
    val film = Film(1, "La banda del openBafici")
    Ok(views.html.show(film))
  }

}