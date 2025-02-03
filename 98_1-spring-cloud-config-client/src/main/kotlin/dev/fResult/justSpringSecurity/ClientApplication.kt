package dev.fResult.justSpringSecurity

import dev.fResult.justSpringSecurity.configs.AppConfigServerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(value = [AppConfigServerProperties::class])
class ClientApplication

fun main(args: Array<String>) {
  runApplication<ClientApplication>(*args)
}
