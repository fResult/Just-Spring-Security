package dev.fResult.justSpringSecurity.videos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.access.prepost.PreAuthorize

interface VideoRepository : JpaRepository<Video, Long> {
  fun findByNameContainsIgnoreCase(name: String): List<Video>

  fun findByDescriptionContainsIgnoreCase(description: String): List<Video>

  fun findByNameContainsOrDescriptionContainsAllIgnoreCase(name: String, description: String): List<Video>

  @PreAuthorize("#entity.username == authentication.name")
  override fun delete(entity: Video)
}
