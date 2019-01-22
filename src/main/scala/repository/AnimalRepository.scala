package repository

import model.{Animal, Monster, Pokemon}

object AnimalRepository {
  private val psykokwak = Pokemon(1, "Psykokwak", "\uD83E\uDD86", 10)
  private val miaouss = Pokemon(2, "Miaouss", "\uD83D\uDC31", 20)
  private val keldeo = Pokemon(3, "Keldeo", "\uD83E\uDD84", 30)
  private val pokemons = List(psykokwak, miaouss, keldeo)

  private val razowski = Monster(4, "Bob Razowski", "\uD83D\uDC41️")
  private val sullivan = Monster(5, "Jacques Sullivan", "\uD83D\uDC7E")
  private val monsters = List(razowski, sullivan)
  private val animals: List[Animal] = pokemons ++ monsters

  def find : List[Animal] = animals
}