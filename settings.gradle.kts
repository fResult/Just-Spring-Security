plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "JustSpringSecurity"

include("01-my-first-security")
include("02-method-security")
include("03-oauth")
include("04-testing-with-junit5")

include("99-event-driven-arch")
