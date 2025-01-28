package dev.fResult.justSpringSecurity.videos

import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {
  fun getVideos(search: String?): List<Video> = search
    ?.let(::convertToVideoExample)
    ?.let(videoRepository::findAll)
    ?: videoRepository.findAll()

  fun create(newVideo: NewVideo, username: String): Video {
    val videoToCreate = Video(username, newVideo.name, newVideo.description)

    return videoRepository.saveAndFlush(videoToCreate)
  }

  fun delete(id: Long): Boolean? = videoRepository.findById(id).map { video ->
    videoRepository.delete(video)
    true
  }.orElseThrow { NoSuchElementException("Video with id [$id] not found") }

  private fun convertToVideoExample(search: String): Example<Video> = Example.of(
    Video(search, search, search),
    ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
  )

  @PostConstruct
  fun initVideos(): Unit {
    val video1 = Video(
      "alice",
      "Need HELP with your SPRING BOOT 3 App?",
      "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."
    )
    val video2 = Video(
      "alice",
      "Don't do THIS to your own CODE!",
      "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"
    )
    val video3 = Video(
      "bob",
      "SECRETS to fix BROKEN CODE!",
      "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."
    )

    videoRepository.saveAll(listOf(video1, video2, video3))
  }

}
