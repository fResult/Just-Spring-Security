package dev.fResult.justSpringSecurity.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties
class AppConfigServerProperties(val my: My = My()) {
  class My(var property: String = "default")
}
