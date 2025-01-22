package dev.fResult.justSpringSecurity.videos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.access.prepost.PreAuthorize

interface VideoRepository : JpaRepository<VideoEntity, Long> {
  @PreAuthorize("#entity.username == authentication.name")
  override fun delete(entity: VideoEntity)
}
