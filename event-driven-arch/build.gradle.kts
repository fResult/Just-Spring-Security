plugins {
  kotlin("jvm") version "1.9.25"
  application
}

group = "dev.fResult"
version = "0.0.1"
description = "Try Event-Driven Architecture"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

application {
  mainClass.set("EventDrivenArchKt")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
