package dev.fResult.justSpringSecurity.videos

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [VideoController::class])
class VideoControllerTest(@Autowired private val mockMvc: MockMvc) {
  @MockitoBean
  private lateinit var videoService: VideoService

  @Test
  @WithMockUser
  fun whenDeleteVideoSuccess_thenReturn204() {
    val videoId = 42L
    `when`(videoService.delete(anyLong())).thenReturn(true)

    mockMvc.delete("/videos/{id}", videoId) {
      status().isNoContent()
    }
  }
}
