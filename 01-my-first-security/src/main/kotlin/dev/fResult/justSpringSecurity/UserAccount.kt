package dev.fResult.justSpringSecurity

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
data class UserAccount(
  @Id
  @GeneratedValue
  val id: Long? = null,
  var username: String,
  var password: String,

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_authorities", joinColumns = [JoinColumn(name = "user_id")])
  @Column(name = "authority")
  var authorities: List<String> = listOf()
) {
  constructor() : this(null, "", "", listOf())

  constructor(username: String, password: String, vararg authorities: String) :
      this(null, username, password, authorities.toList())

  fun asUser(passwordEncoder: PasswordEncoder): UserDetails =
    User.builder().username(username).password(passwordEncoder.encode(password))
      .authorities(authorities.map { SimpleGrantedAuthority(it) }).build()
}
