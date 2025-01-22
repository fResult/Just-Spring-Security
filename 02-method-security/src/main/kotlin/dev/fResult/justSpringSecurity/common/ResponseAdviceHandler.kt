package dev.fResult.justSpringSecurity.common

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ResponseAdviceHandler {
  @ExceptionHandler(NoSuchElementException::class)
  protected fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<Any> {
    val detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.message)
    println("Warning: ${ex.message}")

    return ResponseEntity.of(detail).build()
  }

  @ExceptionHandler(AccessDeniedException::class)
  protected fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<Any> {
    val detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.message)
    println("Warning: ${ex.message}")

    return ResponseEntity.of(detail).build()
  }

  @ExceptionHandler(Exception::class)
  protected fun handleGlobalException(ex: Exception): ResponseEntity<*> {
    val detail =
      ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)

    ex.printStackTrace()

    return ResponseEntity.of(detail).build<Any>()
  }
}
