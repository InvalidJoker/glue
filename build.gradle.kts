import java.util.Calendar

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    `maven-publish`
}

// Taken from https://github.com/TheFruxz/Stacked/blob/develop/build.gradle.kts
val publishVersion: String? = System.getenv("GH_RELEASE_VERSION")
val calendar: Calendar = Calendar.getInstance()
version = publishVersion ?: "${calendar[Calendar.YEAR]}.${calendar[Calendar.MONTH] + 1}-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlinx-serialization")
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")
    apply(plugin = "signing")

    group = "de.joker"
    version = rootProject.version

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    java {
        withSourcesJar()
        withJavadocJar()
        toolchain {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    kotlin {
        jvmToolchain(21)
        sourceSets {
            main {
                kotlin.srcDirs("src")
            }
            test {
                kotlin.srcDirs("test")
            }
        }
    }

    val skipPublish =
        (findProperty("skip.publish") as String?)?.toBooleanStrictOrNull() == true

    if (!skipPublish) {
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    from(components.findByName("java"))

                    versionMapping {
                        usage("java-api") {
                            fromResolutionOf("runtimeClasspath")
                        }
                        usage("java-runtime") {
                            fromResolutionResult()
                        }
                    }

                    groupId = "dev.invalidjoker.glue"
                    artifactId = project.name
                    version = project.version.toString()

                    pom {
                        name.set(project.name)
                        description.set("The ${project.name} project provides various utilities and extensions")
                        url.set("https://github.com/InvalidJoker/glue")
                        licenses {
                            license {
                                name.set("GNU General Public License v3.0")
                                url.set("https://www.gnu.org/licenses/gpl-3.0.html")
                            }
                        }
                        developers {
                            developer {
                                id.set("invalidjoker")
                                name.set("InvalidJoker")
                            }
                            developer {
                                id.set("maxbossing")
                                name.set("Max Bossing")
                            }
                        }
                    }
                }
            }

            repositories {
                val repoUrl = if (project.version.toString().endsWith("SNAPSHOT")) {
                    "https://central.sonatype.com/repository/maven-snapshots/"
                } else {
                    "https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/"
                }
                maven {
                    name = "sonatype"
                    url = uri(repoUrl)
                    credentials {
                        username = findProperty("sonatypeUsername") as String?
                        password = findProperty("sonatypePassword") as String?
                    }
                }
            }
        }

        configure<SigningExtension> {
            sign(publishing.publications)
        }
    } else {
        logger.lifecycle("Skipping publishing for project '${project.name}' because skip.publish=true")
    }

    tasks {
        compileKotlin {
            compilerOptions {
                freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
}