package dev.fResult.justSpringSecurity

import dev.fResult.justSpringSecurity.videos.Video
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoreDomainTest {
  @Test
  fun newVideoShouldHaveNoId(): Unit {
    val video = Video("Alice", "title", "description")

    assertThat(video.id).isNull()
    assertThat(video.username).isEqualTo("Alice")
    assertThat(video.name).isEqualTo("title")
    assertThat(video.description).isEqualTo("description")
  }
}
