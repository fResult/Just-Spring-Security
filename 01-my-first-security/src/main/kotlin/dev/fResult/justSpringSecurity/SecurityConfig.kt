package dev.fResult.justSpringSecurity

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig {
  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @Bean
  fun initUsers(repository: UserManagementRepository): CommandLineRunner? {
    return CommandLineRunner { _ ->
      repository.save(UserAccount("user", "password", "ROLE_USER"))
      repository.save(UserAccount("admin", "password", "ROLE_ADMIN"))
    }
  }

  @Bean
  fun userService(userRepository: UserRepository): UserDetailsService {
    return UserDetailsService { username ->
      userRepository.findByUsername(username)?.asUser(passwordEncoder())
        ?: throw NoSuchElementException("Username or Password is incorrect")
    }
  }
}
