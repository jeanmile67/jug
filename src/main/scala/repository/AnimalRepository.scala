package repository

import model.{Animal, Monster, Pokemon}

object AnimalRepository {
  private val psykokwak = new Pokemon(1, "Psykokwak", "\uD83E\uDD86", 10)
  private val miaouss = new Pokemon(3, "Miaouss", "\uD83D\uDC31", 20)
  private val keldeo = new Pokemon(2, "Keldeo", "\uD83E\uDD84", 30)
  private val pokemons = List(psykokwak, miaouss, keldeo)

  private val razowski = new Monster(4, "Bob Razowski", "\uD83D\uDC41️")
  private val sullivan = new Monster(5, "Jacques Sullivan", "\uD83D\uDC7E")
  val monsters = List(razowski, sullivan)
  val animals: List[Animal] = pokemons ++ monsters

  def find : List[Animal] = animals
}