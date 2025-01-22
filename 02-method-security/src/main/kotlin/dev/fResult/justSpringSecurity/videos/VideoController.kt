package dev.fResult.justSpringSecurity.videos

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/videos")
class VideoController(private val videoService: VideoService) {
  @GetMapping
  fun getVideos(@RequestParam search: String?): ResponseEntity<List<Video>> {
    return ResponseEntity.ok(videoService.getVideos(search))
  }

  @PostMapping
  fun newVideo(@RequestBody newVideo: NewVideo, authentication: Authentication): ResponseEntity<Video> {
    val createdVideo = videoService.create(newVideo, authentication.name)

    return createdVideo.let { ResponseEntity.created(URI.create("/videos/${it.id}")).body(it) }
  }

  @DeleteMapping("/{id}")
  fun deleteVideo(@PathVariable id: Long): ResponseEntity<Void> = videoService.delete(id)
    ?.let {
      ResponseEntity.noContent().build()
    }
    ?: ResponseEntity.notFound().build()
}

