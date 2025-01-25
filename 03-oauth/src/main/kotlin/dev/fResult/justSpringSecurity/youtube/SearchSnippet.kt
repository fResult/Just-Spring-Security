package dev.fResult.justSpringSecurity.youtube

data class SearchSnippet(
  val publishedAt: String,
  val channelId: String,
  val title: String,
  val description: String,
  val thumbnails: Map<String, SearchThumbnail>,
  val channelTitle: String,
) {
  fun shortDescription(): String {
    return if (description.length <= 100) description
    else "${description.substring(0, 100)}..."
  }

  fun thumbnail(): SearchThumbnail? = thumbnails["default"]
}
