package dev.fResult.justSpringSecurity.videos

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class VideoRepositoryTest(@Autowired private val videoRepository: VideoRepository) {
  @BeforeEach
  fun setUp() {
    val video1 = Video(
      "user",
      "Need HELP with your SPRING BOOT 3 App?",
      "SPRING BOOT 3 will only speed things up.",
    )
    val video2 = Video(
      "user",
      "Don't do THIS to your own CODE!",
      "As a pro developer, never ever EVER do this to your code.",
    )
    val video3 = Video(
      "user", "SECRETS to fix BROKEN CODE!", "Discover ways to not only debug your code"
    )

    videoRepository.saveAll(listOf(video1, video2, video3))
  }

  @Test
  fun `find all should produce all videos`() {
    // When
    val videos = videoRepository.findAll()

    // Then
    assertThat(videos).hasSize(3)
  }

  @Test
  fun `find by name should retrieve one entry`() {
    // When
    val videos = videoRepository.findByNameContainsIgnoreCase("Spring BOOT 3")

    // Then
    assertThat(videos).hasSize(1)
    assertThat(videos)
      .extracting(Video::name)
      .containsExactlyInAnyOrder(Tuple("Need HELP with your SPRING BOOT 3 App?"))
  }
}
