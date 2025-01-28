package dev.fResult.justSpringSecurity.videos

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [VideoController::class])
class VideoControllerTest(
  @Autowired private val mockMvc: MockMvc,
  @Autowired private val objectMapper: ObjectMapper,
) {
  @MockitoBean
  private lateinit var videoService: VideoService

  @Test
  @WithMockUser(username = "Alice", roles = ["USER"])
  @Throws(Exception::class)
  fun newVideoShouldWork() {
    val requestBody = NewVideo("New Video", "Description")

    mockMvc.perform(
      post("/videos").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestBody))
    )

    verify(videoService).create(requestBody, "Alice")
  }

  @Test
  @WithMockUser(username = "Alice", roles = ["USER"])
  @Throws(Exception::class)
  fun whenDeleteVideoSuccess_thenReturn204() {
    val videoId = 42L
    `when`(videoService.delete(anyLong())).thenReturn(true)

    mockMvc.perform(delete("/videos/{id}", videoId).with(csrf()))
      .andExpect(status().isNoContent)
  }
}
