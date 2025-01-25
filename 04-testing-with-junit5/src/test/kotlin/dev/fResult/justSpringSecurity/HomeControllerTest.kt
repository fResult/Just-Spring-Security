package dev.fResult.justSpringSecurity

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HomeController::class)
class HomeControllerTest(@Autowired val mockMvc: MockMvc) {
  @MockitoBean
  lateinit var userRepository: UserRepository

  @Test
  @WithMockUser
  @Throws(Exception::class)
  fun shouldReturnHelloWorld() {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
      .andExpect(content().string("Hello, World!"))
  }
}
