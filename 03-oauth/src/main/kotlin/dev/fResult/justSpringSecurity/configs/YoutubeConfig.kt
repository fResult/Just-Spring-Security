package dev.fResult.justSpringSecurity.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class YoutubeConfig {
  private companion object {
    const val YOUTUBE_V3_API: String = "https://googleapis.com/youtube/v3"
  }

  @Bean
  fun webClient(oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager): WebClient {
    val oAuth2 = ServletOAuth2AuthorizedClientExchangeFilterFunction(oAuth2AuthorizedClientManager)

    return WebClient.builder()
      .baseUrl(YOUTUBE_V3_API)
      .apply(oAuth2.oauth2Configuration())
      .build()
  }
}
