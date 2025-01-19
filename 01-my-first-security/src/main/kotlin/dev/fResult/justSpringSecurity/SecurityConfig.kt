package dev.fResult.justSpringSecurity

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class SecurityConfig {
  @Bean
  fun initUsers(repository: UserManagementRepository): CommandLineRunner? {
    return CommandLineRunner { _ ->
      repository.save(UserAccount("user", "password", "ROLE_USER"))
      repository.save(UserAccount("admin", "password", "ROLE_ADMIN"))
    }
  }
}
