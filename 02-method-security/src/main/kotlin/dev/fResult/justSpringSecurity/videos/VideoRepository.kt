package dev.fResult.justSpringSecurity.videos

import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository : JpaRepository<VideoEntity, Long>
