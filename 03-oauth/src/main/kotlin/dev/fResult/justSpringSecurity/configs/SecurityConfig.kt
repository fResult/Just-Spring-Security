package dev.fResult.justSpringSecurity.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository

@Configuration
class SecurityConfig {
  @Bean
  fun oAuth2AuthorizedClientManager(
    clientRegRepository: ClientRegistrationRepository,
    authClientRepository: OAuth2AuthorizedClientRepository
  ): OAuth2AuthorizedClientManager {
    val clientManager = DefaultOAuth2AuthorizedClientManager(clientRegRepository, authClientRepository)
    val clientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
      .authorizationCode()
      .refreshToken()
      .clientCredentials()
      .password()
      .build()

    clientManager.setAuthorizedClientProvider(clientProvider)

    return clientManager
  }
}
