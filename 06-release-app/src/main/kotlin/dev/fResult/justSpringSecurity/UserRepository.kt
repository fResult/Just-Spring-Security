package dev.fResult.justSpringSecurity

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserAccount, Long> {
  fun findByUsername(username: String): UserAccount?
}
