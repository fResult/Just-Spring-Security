package dev.fResult.justSpringSecurity

import dev.fResult.justSpringSecurity.common.configs.AppConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppConfigProperties::class)
class Application

fun main(args: Array<String>) {
  runApplication<Application>(*args)
}
