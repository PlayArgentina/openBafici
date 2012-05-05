package controllers

import play.api._
import play.api.mvc._

import models.Film

object Films extends Controller {
  
  def home() = Action {
    Redirect(routes.Films.list(1,"",""))
  }
  
  def list(page: Long = 1, sort: String = "", filter: String = "") = Action {
    
    val client = new ar.com.restba.DefaultRestBAClient("http://zenithsistemas.com:9200")
    val connection = client.fetchConnectionRestBaAsJson(filter, page)
    println(connection.getMaxPages())
    val firstPageIterator = connection.iterator()
    
    val firstPage = firstPageIterator.next().iterator()
    
    var l = List[Film]()
    
    while(firstPage.hasNext()) {
      val item = firstPage.next() 
      println("JSON: " + item)
      try {
      val film = Film(
	      0, 
		  "unknown film",
		   item.getString("name_es"),
		  "http://google.com",
		   1984,
		   "ninguno genero",
		   "Van Damme",
		   "werwerwer",
		  "image.png",
		   "Scorcese",
		  "sinopsis",
		  "sinopsis",
		  95,
		  "Fulano",
		   "20-20-1945"
      )
      
      l =  film :: l
       } catch { 
       case e:Exception => println("json apping error.")
       } 
      
    }
        
    Ok(views.html.list(page, sort, filter, List[Film]()))
  }

  def show(id: String) = Action {
    val film = Film(1, "La banda del openBafici")
    Ok(views.html.show(film))
  }

}