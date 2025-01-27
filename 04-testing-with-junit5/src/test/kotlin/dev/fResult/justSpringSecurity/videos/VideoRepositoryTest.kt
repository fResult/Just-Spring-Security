package dev.fResult.justSpringSecurity.videos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class VideoRepositoryTest(@Autowired private val videoRepository: VideoRepository) {
  @BeforeEach
  fun setUp() {
    val video1 = Video("Alice", "video 1", "description")
    val video2 = Video("Alice", "video 2", "description")
    val video3 = Video("Alice", "video 3", "description")

    videoRepository.saveAll(listOf(video1, video2, video3))
  }

  @Test
  fun `find all should produce all videos`() {
    // When
    val videos = videoRepository.findAll()

    // Then
    assertThat(videos).hasSize(3)
  }
}
