package dev.fResult.justSpringSecurity

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController(val userRepository: UserRepository) {
  @GetMapping
  fun home(): ResponseEntity<String> =
    ResponseEntity.ok("Hello, World!")

  @GetMapping("/users")
  fun getUser(@RequestParam username: String): ResponseEntity<UserInfo> = userRepository.findByUsername(username)
    ?.let { ResponseEntity.ok(UserInfo.fromDao(it)) } ?: ResponseEntity.notFound().build()
}
