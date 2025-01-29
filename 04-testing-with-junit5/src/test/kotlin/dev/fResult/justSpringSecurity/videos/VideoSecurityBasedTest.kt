package dev.fResult.justSpringSecurity.videos

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [VideoController::class])
class VideoSecurityBasedTest(
  @Autowired val mockMvc: MockMvc,
  @Autowired val objectMapper: ObjectMapper,
) {
  @MockitoBean
  private lateinit var videoService: VideoService

  @Test
  @Throws(Exception::class)
  fun `unauthenticated user should not access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isUnauthorized)
  }

  @Test
  @WithMockUser(username = "Alice", roles = ["USER"])
  @Throws(Exception::class)
  fun `authenticated user should access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isOk())
  }

  @Test
  @WithMockUser(username = "Admin", roles = ["ADMIN"])
  @Throws(Exception::class)
  fun `admin user should access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isOk)
  }

  @Test
  @Throws(Exception::class)
  fun `new video should be forbidden for unauthenticated user`() {
    mockMvc.perform(post("/videos").with(csrf()))
      .andExpect(status().isUnauthorized)
  }

  @Test
  @WithMockUser(username = "Alice", roles = ["USER"])
  @Throws(Exception::class)
  fun `new video from authenticated user should work`() {
    val requestBody = NewVideo("New Video", "New Video Description")
    val createdVideo = Video(1, "Alice", "New Video", "New Video Description")

    given(videoService.create(requestBody, "Alice")).willReturn(createdVideo)

    mockMvc.perform(
      post("/videos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestBody))
        .with(csrf())
    ).andExpect(status().isCreated)
      .andExpect(jsonPath("$.id").value(createdVideo.id))
  }
}
