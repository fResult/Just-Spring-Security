import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  kotlin("jvm") version "1.9.25"
  kotlin("plugin.spring") version "1.9.25"
  id("org.springframework.boot") version libs.versions.springboot.get()
  id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.fResult"
version = "0.0.1"
description = "Securing an Application with Spring Boot and Method Security"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.spring.boot.starter.security)
  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.data.jpa)
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  runtimeOnly("com.h2database:h2")

  testImplementation(libs.spring.boot.starter.test)
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.springframework.security:spring-security-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<BootJar> {
  mainClass = "dev.fResult.justSpringSecurity.ApplicationKt"
}
