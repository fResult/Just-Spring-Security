package dev.fResult.justSpringSecurity.videos

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class VideoServiceTest {
  private lateinit var videoService: VideoService
  @Mock private lateinit var videoRepository: VideoRepository

  @BeforeEach
  fun setUp() {
    videoService = VideoService(videoRepository)
  }

  @Test
  fun `should save video`() {
    val newVideo =  NewVideo("title", "description")
    val videoToCreate = Video("Alice", "title", "description")
    val expectedCreatedVideo = Video(1, "Alice", "title", "description")
    `when`(videoRepository.saveAndFlush(eq(videoToCreate))).thenReturn(expectedCreatedVideo)

    val actualCreatedVideo = videoService.create(newVideo, "Alice")

    assertEquals(expectedCreatedVideo, actualCreatedVideo)
  }
}
