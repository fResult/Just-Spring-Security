plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "JustSpringSecurity"

include("01-my-first-security")
include("02-method-security")
include("03-oauth")
include("04-testing-with-junit5")
include("05-configure-app")
include("06-release-app")
include("07-going-graalvm")

// Off-topics
include("98-config-ext-with-spring-cloud")
include("98_1-spring-cloud-config-client")
include("99-event-driven-arch")
