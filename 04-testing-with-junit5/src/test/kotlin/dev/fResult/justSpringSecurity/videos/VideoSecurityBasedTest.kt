package dev.fResult.justSpringSecurity.videos

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [VideoController::class])
class VideoSecurityBasedTest(@Autowired val mockMvc: MockMvc) {
  @MockitoBean
  private lateinit var videoService: VideoService

  @Test
  fun `unauthenticated user shouldn't access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isUnauthorized)
  }

  @Test
  @WithMockUser(username = "Alice", roles = ["USER"])
  fun `authenticated user should access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isOk())
  }

  @Test
  @WithMockUser(username = "Admin", roles = ["ADMIN"])
  fun `admin user should access videos`() {
    mockMvc.perform(get("/videos")).andExpect(status().isOk)
  }
}
