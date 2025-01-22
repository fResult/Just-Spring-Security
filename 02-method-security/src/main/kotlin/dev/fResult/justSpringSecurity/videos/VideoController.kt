package dev.fResult.justSpringSecurity.videos

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/videos")
class VideoController(private val videoService: VideoService) {
  @GetMapping
  fun getVideos(): ResponseEntity<List<VideoEntity>> = ResponseEntity.ok(videoService.getVideos())

  @PostMapping
  fun newVideo(@RequestBody newVideo: NewVideo, authentication: Authentication): ResponseEntity<VideoEntity> {
    val createdVideo = videoService.create(newVideo, authentication.name)

    return createdVideo.let { ResponseEntity.created(URI.create("/videos/${it.id}")).body(it) }
  }
}
