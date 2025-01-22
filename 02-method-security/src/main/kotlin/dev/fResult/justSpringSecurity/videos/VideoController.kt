package dev.fResult.justSpringSecurity.videos

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/videos")
class VideoController(private val videoService: VideoService) {
  @RequestMapping
  fun getVideos(): ResponseEntity<List<VideoEntity>> = ResponseEntity.ok(videoService.getVideos())
}
