package dev.fResult.justSpringSecurity.videos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = [VideoRepositoryTestContainersTest.DataSourceInitializer::class])
class VideoRepositoryTestContainersTest(
  @Autowired private val videoRepository: VideoRepository,
) {
  companion object {
    @Container
    val POSTGRESQL_CONTAINER: PostgreSQLContainer<*> =
      PostgreSQLContainer<Nothing>("postgres:16-alpine").withUsername("postgres")
  }

  @BeforeEach
  fun setUpEach() {
    videoRepository.saveAll(
      listOf(
        Video(
          "alice",
          "Need HELP with your SPRING BOOT 3 App?",
          "SPRING BOOT 3 will only speed things up."
        ),
        Video(
          "alice",
          "Don't do THIS to your own CODE!",
          "As a pro developer, never ever EVER do this to your code."
        ),
        Video(
          "bob",
          "SECRETS to fix BROKEN CODE!",
          "Discover ways to not only debug your code"
        )
      )
    )
  }

  @Test
  fun `find all should Produce all videos`() {
    val videos = videoRepository.findAll()
    assertThat(videos).hasSize(3)
  }

  @Test
  fun `find by name should return one`() {
    val videos = videoRepository.findByNameContainsIgnoreCase("Spring BOOT 3")
    assertThat(videos).hasSize(1)
  }

  @Test
  fun `find by name or description should return two`() {
    val videos = videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase("cOdE", "YOUR code")
    assertThat(videos).hasSize(2)
  }

  class DataSourceInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
      TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
        applicationContext,
        "spring.datasource.url=${POSTGRESQL_CONTAINER.getJdbcUrl()}",
        "spring.datasource.username=${POSTGRESQL_CONTAINER.username}",
        "spring.datasource.password=${POSTGRESQL_CONTAINER.password}",
        "spring.jpa.hibernate.ddl-auto=create-drop",
      )
    }
  }
}
