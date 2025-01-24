package dev.fResult.justSpringSecurity.youtube

/**
 * @see <a href="https://developers.google.com/youtube/v3/docs/search#resource-representation">YouTube Data API - Search: Resource representation</a>
 */
data class SearchResult(val kind: String, val etag: String, val id: SearchId, val snippet: SearchSnippet)
