package dev.fResult.justSpringSecurity.videos

import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {
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
