package dev.fResult.justSpringSecurity.videos

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {
  @PostConstruct
  fun initVideos(): Unit {
    videoRepository.saveAll(
      listOf(
        VideoEntity(
          "alice",
          "Need HELP with your SPRING BOOT 3 App?",
          "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."
        ),
        VideoEntity(
          "alice",
          "Don't do THIS to your own CODE!",
          "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"
        ),
        VideoEntity(
          "bob",
          "SECRETS to fix BROKEN CODE!",
          "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."
        )
      )
    )
  }

  fun getVideos(): List<VideoEntity> {
    return videoRepository.findAll()
  }

  fun create(newVideo: NewVideo, username: String): VideoEntity {
    val videoToCreate = VideoEntity(username, newVideo.name, newVideo.description)

    return videoRepository.saveAndFlush(videoToCreate)
  }

  fun delete(id: Long): Boolean? = videoRepository.findById(id).map { video ->
    videoRepository.delete(video)
    true
  }.orElseThrow { NoSuchElementException("Video with id [$id] not found") }
}
