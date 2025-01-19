package dev.fResult.justSpringSecurity

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {
  @GetMapping
  fun home(): ResponseEntity<String> {
    return ResponseEntity.ok("Hello, World!")
  }
}
