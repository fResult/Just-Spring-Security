package dev.fResult.justSpringSecurity.videos

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Video(
  @Id @GeneratedValue val id: Long?,
  val username: String,
  val name: String,
  val description: String
) {
  constructor() : this(null, "", "", "")
  constructor(username: String, name: String, description: String) : this(null, username, name, description)
}
