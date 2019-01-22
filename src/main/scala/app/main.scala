package main

import model.Animal
import repository.AnimalRepository
import cats.effect.IO

import scala.collection.immutable
import scala.io.StdIn._
import scala.util.{Random, Try}

object main {
  val animals: List[Animal] = AnimalRepository.find
  def parseInt(value: String): Option[Int] = Try(value.toInt).toOption

  def putStrlLn(value: String): IO[Unit] = IO(println(value))
  val readLn: IO[String] = IO(scala.io.StdIn.readLine)

  def response(animals: List[Animal]): IO[Animal] = IO(Random.shuffle(animals).head)

  def startGame: IO[Unit] = for {
    _ <- putStrlLn("Quel est votre nom ?")
    name <- readLn
    _ <- putStrlLn("Hello, " + name + ", bienvenue dans la partie!")
  } yield ()

  def gameLoop(name: String) = for {
    response <- response(animals)
    _ <- putStrlLn(s"$name, vous devez devinez un animal parmi la liste suivante [${animals.mkString(", ")}]")
    input <- readLn
    _ <- parseInt(input) match {
      case None => println("Vous n'avez pas rentrer de nombre")
      case Some(guess) =>
        if (guess == response.id) println("Bonne réponse, " + name + "!")
        else println("Mauvaise réponse, " + name + "! L'animal était : " + response)
    }
  } yield ()


  def main(args: Array[String]) {
    val name = "tmp"

    var exec = true
    while (exec) {

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
