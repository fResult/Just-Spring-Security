package dev.fResult.justSpringSecurity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/client")
class ClientController(private val clientService: ClientService) {
  @GetMapping("/property")
  fun getProperty(): String {
    clientService.printProperty()

    return "Property value printed in logs."
  }
}
