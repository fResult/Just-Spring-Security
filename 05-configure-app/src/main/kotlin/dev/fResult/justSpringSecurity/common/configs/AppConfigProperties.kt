package dev.fResult.justSpringSecurity.common.configs

import dev.fResult.justSpringSecurity.UserAccount
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.config")
data class AppConfigProperties(val header: String, val intro: String, val users: List<UserAccount>)
