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
      "UC_x5XG1OV2P6uZZ5FSM9Ttw",
      10, Youtube.Sort.VIEW_COUNT,
    )

    model.addAttribute("channelVideos", response)

    return "youtube"
  }
}
