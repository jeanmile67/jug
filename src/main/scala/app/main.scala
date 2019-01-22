package main

import model.Animal
import repository.AnimalRepository
import scala.io.StdIn._
import scala.util.Random

object main {

  def main(args: Array[String]) {
    val animals: List[Animal] = AnimalRepository.find

    println("Quel est votre nom ?")

    val name: String = readLine()

    println("Hello, " + name + ", bienvenue dans la partie!")

    var exec = true
    while (exec) {
      println(s"$name, vous devez devinez un animal parmi la liste suivante [${animals.mkString(", ")}]")

      val guess = readLine().toInt

      val response = Random.shuffle(animals).head
      if (guess == response.id) println("Bonne réponse, " + name + "!")
      else println("Mauvaise réponse, " + name + "! L'animal était : " + response)

      println("Voulez vous continuez, " + name + " ?")

      readLine() match {
        case "y" => exec = true
        case "n" => exec = false
      }
    }
  }
}
