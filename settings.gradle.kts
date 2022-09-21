rootProject.name = "osm"

include("osm-core", "osm-boot")

project(":osm-core").projectDir = File("$rootDir/osm-core")
project(":osm-boot").projectDir = File("$rootDir/osm-boot")

pluginManagement {
  plugins {
    kotlin("jvm") version "${extra["version.kotlin"]}"
    id("org.springframework.boot") version "${extra["version.spring.boot"]}"
    id("com.github.ben-manes.versions") version "${extra["version.versions-plugin"]}"
    id("com.github.johnrengelman.shadow") version "5.2.0"
  }
}
