package dev.fResult.justSpringSecurity

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ClientService {
  /**
   * `my.property` is the application property which is stored in [GitHub Repository](https://github.com/fResult/Spring-Configuration-Externalization)
   */
  @Value("\${my.property}")
  private val myProperty: String? = null

  fun printProperty() {
    println("Property value: $myProperty")
  }
}
