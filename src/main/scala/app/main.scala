package main

import model.Animal
import repository.AnimalRepository
import cats.effect.IO
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
    _ <- gameLoop(name)
  } yield ()

  def gameLoop(name: String): IO[Unit] = for {
    response <- response(animals)
    _ <- putStrlLn(s"$name, vous devez devinez un animal parmi la liste suivante [${animals.mkString(", ")}]")
    input <- readLn
    _ <- parseInt(input) match {
      case None => putStrlLn("Vous n'avez pas rentrer de nombre")
      case Some(guess) =>
        if (guess == response.id) putStrlLn("Bonne réponse, " + name + "!")
        else putStrlLn("Mauvaise réponse, " + name + "! L'animal était : " + response)
    }
    continue <- checkContinue(name)
    _ <- if (continue) gameLoop(name) else IO.pure(())
  } yield ()

  def checkContinue(name: String):IO[Boolean] = for {
    _ <- putStrlLn("Voulez vous continuez, " + name + " ?")
    input <- readLn
    continue <- input match {
      case "y" => IO.pure(true)
      case "n" => IO.pure(false)
      case _ => checkContinue(name)
    }
  } yield continue

  def main(args: Array[String]) = startGame.unsafeRunSync()
}
