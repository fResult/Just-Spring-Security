package dev.fResult.justSpringSecurity.youtube

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/youtube")
class YoutubeController(private val youtube: Youtube) {
  @GetMapping
  fun youtubeHome(model: Model): String {
    val response = youtube.channelVideos(
      "UCLMPXsvSrhNPN3i9h-u8PYg", // Spring I/O Channel ID
      10, Youtube.Sort.VIEW_COUNT,
    )

    model.addAttribute("channelVideos", response)

    return "youtube"
  }
}
