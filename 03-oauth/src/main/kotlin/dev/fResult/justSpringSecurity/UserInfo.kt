package dev.fResult.justSpringSecurity

data class UserInfo(val username: String, val role: String) {
  companion object {
    fun fromDao(userAccount: UserAccount): UserInfo =
      UserInfo(username = userAccount.username, role = userAccount.authorities.first())
  }
}
