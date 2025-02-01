plugins {
  java
  id("org.springframework.boot") version libs.versions.springboot.get()
  id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Configuration Externalization with Spring Cloud Config Server"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.cloud:spring-cloud-config-server")

  testImplementation(libs.spring.boot.starter.test)

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.spring.cloud.get()}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
