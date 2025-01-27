package dev.fResult.justSpringSecurity.videos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class VideoServiceTest {
  private lateinit var videoService: VideoService

  @Mock
  private lateinit var videoRepository: VideoRepository

  @BeforeEach
  fun setUp() {
    videoService = VideoService(videoRepository)
  }

  @Test
  fun `creating new video should return same data with id`() {
    // Given
    val newVideo = NewVideo("title", "description")
    val videoToCreate = Video("Alice", "title", "description")
    val expectedCreatedVideo = Video(1, "Alice", "title", "description")
    given(videoRepository.saveAndFlush(eq(videoToCreate))).willReturn(expectedCreatedVideo)

    // When
    val actualCreatedVideo = videoService.create(newVideo, "Alice")

    // Then
    assertThat(expectedCreatedVideo).isEqualTo(actualCreatedVideo)
  }

  @Test
  fun `delete video should return null if video not found`() {
    // Given
    val videoId = 1L
    given(videoRepository.findById(videoId)).willReturn(Optional.empty())

    // When
    val actualExecutable: () -> Unit =  { videoService.delete(videoId) }

    // Then
    assertThrows<NoSuchElementException>(actualExecutable)
  }
}
