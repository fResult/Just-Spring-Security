package dev.fResult.justSpringSecurity.common.configs

import dev.fResult.justSpringSecurity.UserAccount
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.config")
data class AppConfigProperties(val users: List<UserAccount>) {
  companion object {
    data class UserAccount(val username: String, val password: String, val roles: List<UserRole>) {
      fun toDAO() = UserAccount(null, username, password, roles.map { it.role })
    }

    enum class UserRole(val role: String) {
      ADMIN("ROLE_ADMIN"),
      USER("ROLE_USER")
    }
  }
}
