package dev.fResult.justSpringSecurity.common.configs

import dev.fResult.justSpringSecurity.videos.Video
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.config")
data class AppProperties(val header: String, val intro: String, val videos: List<Video>) {}
