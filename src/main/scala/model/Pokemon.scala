package model

case class Pokemon (
  id: Int,
  name: String,
  emoji: String,
  level: Int
) extends Animal