package dev.fResult.justSpringSecurity.youtube

/**
 * @see <a href="https://developers.google.com/youtube/v3/docs/search/list#response">YouTube Data API - Search: list: Response</a>
 */
data class SearchListResponse(
  val kind: String?,
  val etag: String?,
  val nextPageToken: String?,
  val prevPageToken: String?,
  val pageInfo: PageInfo?,
  val items: List<SearchResult>
)
