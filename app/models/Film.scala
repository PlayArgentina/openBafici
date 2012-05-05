package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Film(
  id: String = "2732fbf4-4e55-4794-8e98-e5d5fa6a0419-57", 
  title: String = "Las Marimbas del Infierno",
  title_es: String = "Las Marimbas del Infierno",
  url_ticket: String = "http://www.tuentrada.com/bafici/online/searchresults.asp?doWork::WScontent::search=1&BOset::WScontent::SearchCriteria::search_criteria=Marimbas",
  year: Long = 1984,
  generes_list: String = "ninguno genero",
  cast: String = "Alfonso Tunch�, Blacko Gonz�lez, V�ctor Hugo Monterroso",
  id_youtube: String = "SWKVzUHXplI",
  filepic1: String = "las_marimbas_del_infierno_1.jpg",
  prodteam: String = "Scorcese",
  synopsis_es: String = "La marimba es un instrumento musical, suerte de xilof�n de madera, tradicional de Guatemala. Pero �tradicional� significa �popular� cada vez menos, y Don Alfonso, marimbista de toda la vida, se las est� viendo negras para vivir de su m�sica. Para peor, v�ctima de una extorsi�n, tiene que pasar casi a la clandestinidad; eso s�, con su pesada marimba a cuestas. Blacko tiene problemas parecidos pero diferentes: fue pionero del heavy metal, pas� del satanismo al cristianismo y de ah� a la ortodoxia jud�a, y ahora trabaja en un hospital, siempre con las mismas estrecheces econ�micas. Lo �nico que parece unir a los dos es el amor a la m�sica. Y, por improbable que suene, esa pasi�n los une cuando, a instancias del tercer personaje de esta historia, un pilluelo apodado Chiquil�n, forman la primera banda de �metal/marimba� de la historia: Las Marimbas del Infierno. La segunda pel�cula de Cord�n se sirve de la realidad (Alfonso y Blacko son realmente m�sicos) para crear una s�tira imaginativa sobre las dificultades del arte en su pa�s, con tanto humor como, claro, tanta y sorprendente m�sica.",
  synopsis_en: String = "The marimba is a musical instrument, a sort of wooden xylophone that�s typical of Guatemala. But �traditional� hardly means �popular� anymore, and Don Alfonso, a life-long marimba player, is finding it hard to make a living out of his music. To make matters worse, he�s the victim of an extortion that forces him to lead an almost clandestine life; and yet he still carries his heavy marimba on his back. Blacko has some similar and yet different problems: he was a heavy metal pioneer who went from Satanist to Catholicism and later to a Jewish orthodoxy, and now works in a hospital, constantly in financial trouble. The only thing that seems to unite them is their love for music. And, as improbable as that sounds, that passion gets them together when they form the first ever heavy metal/marimba band, Marimbas from Hell, thanks to the third character in this story, a little rascal named Chiquil�n. Cord�n�s second film uses reality (Alfonso and Blacko are real musicians) in order to create an imaginative satire about the hardships of art in his country, with as much humour as, of course, lots of surprising music.",
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
    get[String]("film.id") ~
    get[String]("film.name") map {
      case id~name => Film(id, name)
    }
  }
  
  
}

