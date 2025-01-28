package dev.fResult.justSpringSecurity.videos

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.jvm.Throws

@WebMvcTest(controllers = [VideoController::class])
class VideoSecurityBasedTest(@Autowired val mockMvc: MockMvc) {
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
    val requestBody = NewVideo("New Video", "New Video Description")
    mockMvc.perform(post("/videos").with(csrf()))
      .andExpect(status().isUnauthorized)
  }
}
