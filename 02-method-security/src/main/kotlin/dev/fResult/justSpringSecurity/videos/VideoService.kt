package dev.fResult.justSpringSecurity.videos

import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {
    fun getVideos(): List<VideoEntity> {
        return videoRepository.findAll()
    }
}
