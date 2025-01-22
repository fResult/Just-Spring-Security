plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "JustSpringSecurity"

include("01-my-first-security")

include("99-event-driven-arch")
