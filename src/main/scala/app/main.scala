package main

import model.Animal
import repository.AnimalRepository
import cats.effect.IO

import scala.collection.immutable
import scala.io.StdIn._
import scala.util.{Random, Try}

object main {

  def parseInt(value: String): Option[Int] = Try(value.toInt).toOption

  def putStrlLn(value: String): IO[Unit] = IO(println(value))
  val readLn: IO[String] = IO(scala.io.StdIn.readLine)

  def startGame: IO[Unit] = for {
    _ <- putStrlLn("Quel est votre nom ?")
    name <- readLn
    _ <- putStrlLn("Hello, " + name + ", bienvenue dans la partie!")
  } yield ()

  def main(args: Array[String]) {
    val animals: List[Animal] = AnimalRepository.find

    val name = "tmp"
    var exec = true
    while (exec) {
      val response = Random.shuffle(animals).head
      println(s"$name, vous devez devinez un animal parmi la liste suivante [${animals.mkString(", ")}]")

      val guess = parseInt(readLine) match {
        case None => println("Vous n'avez pas rentrer de nombre")
        case Some(guess) =>
          if (guess == response.id) println("Bonne réponse, " + name + "!")
          else println("Mauvaise réponse, " + name + "! L'animal était : " + response)
      }

      var cont = true
      while(cont) {
        cont = false
        println("Voulez vous continuez, " + name + " ?")

        readLine() match {
          case "y" => exec = true
          case "n" => exec = false
          case _ => cont = true
        }
      }
    }
  }
}
