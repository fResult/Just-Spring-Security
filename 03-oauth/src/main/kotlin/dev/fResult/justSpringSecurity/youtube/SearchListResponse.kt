package dev.fResult.justSpringSecurity.youtube

data class SearchListResponse(
  val kind: String,
  val etag: String,
  val nextPageToken: String,
  val prevPageToken: String,
  val pageInfo: PageInfo,
  val items: List<SearchResult>
)
