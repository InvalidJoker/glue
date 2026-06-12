> WIP: But basicly, I imported https://github.com/TheFruxz/Ascend and split it up and added custom paper & adventure modules. Just don't use this just yet

## Setup

```kt
repositories {
    mavenCentral()
    maven("https://repo.koder.wtf/snapshots")
}

dependencies {
    implementation("dev.invalidjoker.glue:core:2026.6-SNAPSHOT")
    implementation("dev.invalidjoker.glue:paper:2026.6-SNAPSHOT")
     implementation("dev.invalidjoker.glue:adventure:2026.6-SNAPSHOT")
}
```