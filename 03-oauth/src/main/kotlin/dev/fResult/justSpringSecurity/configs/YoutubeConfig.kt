package dev.fResult.justSpringSecurity.configs

import dev.fResult.justSpringSecurity.youtube.Youtube
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@Configuration
class YoutubeConfig {
  private companion object {
    const val YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3"
  }

  @Bean
  fun webClient(oAuth2AuthorizedClientManager: OAuth2AuthorizedClientManager): WebClient {
    val oAuth2 = ServletOAuth2AuthorizedClientExchangeFilterFunction(oAuth2AuthorizedClientManager)

    oAuth2.setDefaultClientRegistrationId("google")

    return WebClient.builder()
      .baseUrl(YOUTUBE_V3_API)
      .apply(oAuth2.oauth2Configuration())
      .build()
  }

  @Bean
  fun proxyFactory(webClient: WebClient): HttpServiceProxyFactory =
    HttpServiceProxyFactory.builder().exchangeAdapter(WebClientAdapter.create(webClient)).build()

  @Bean
  fun youtube(proxyFactory: HttpServiceProxyFactory): Youtube =
    proxyFactory.createClient(Youtube::class.java)
}
