package dev.fResult.justSpringSecurity

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

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

  @Bean
  @Throws(Exception::class)
  fun defaultSecurityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain? {
    httpSecurity {
      authorizeHttpRequests { authorize(anyRequest, authenticated) }
      formLogin {}
      httpBasic {}
    }

    return httpSecurity.build()
  }

  // NOTE: For testing in-memory database
  @Bean
  fun webSecurityCustomizer(): WebSecurityCustomizer {
    return WebSecurityCustomizer {
      it.ignoring().requestMatchers(AntPathRequestMatcher("/h2-console/**"))
    }
  }
}
