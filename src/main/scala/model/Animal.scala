package model

trait Animal{
  val id: Int
  val name: String
  val emoji: String

  override def toString: String = s"$id-$name-$emoji"
}
