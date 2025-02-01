package dev.fResult.justSpringSecurity

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ClientService {
  @Value("\${my.property}")
  private val myProperty: String? = null

  fun printProperty() {
    println("Property value: $myProperty")
  }
}
