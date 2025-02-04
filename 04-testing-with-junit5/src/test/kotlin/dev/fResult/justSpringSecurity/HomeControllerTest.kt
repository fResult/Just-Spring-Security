package dev.fResult.justSpringSecurity

import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HomeController::class)
class HomeControllerTest(@Autowired private val mockMvc: MockMvc) {
  @MockitoBean
  private lateinit var userRepository: UserRepository

  @Test
  @WithMockUser
  @Throws(Exception::class)
  fun shouldReturnHelloWorld() {
    mockMvc.get("/") {
      status().isOk()
      content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
      content().string("Hello, World!")
    }
  }

  @Test
  @WithMockUser
  @Throws(Exception::class)
  fun shouldReturnUserInfo() {
    `when`(userRepository.findByUsername(anyString())).thenReturn(UserAccount("Alice", "password", "USER"))

    mockMvc.get("/users") {
      param("username", "Alice")
      status().isOk()
      content().contentType(MediaType.APPLICATION_JSON)
      jsonPath("$.username").value("Alice")
      jsonPath("$.role").value("USER")
    }
  }
}
