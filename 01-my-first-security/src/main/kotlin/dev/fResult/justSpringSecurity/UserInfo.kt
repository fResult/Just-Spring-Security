package dev.fResult.justSpringSecurity

data class UserInfo(val username: String) {
  companion object {
    fun fromDao(userAccount: UserAccount): UserInfo = UserInfo(userAccount.username)
  }
}
