plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "glue"

// Core:
include(":core")
include(":ext-utils")

// Minecraft:
include(":adventure")
include(":paper")