plugins {
    id("com.android.application") version libs.versions.android.gradlePlugin
    kotlin("android") version libs.versions.kotlin.target
    kotlin("plugin.serialization") version libs.versions.kotlin.target
}

buildscript {
    dependencies {
        classpath("local.buildsrc:conventions")
    }
}

setupTachiyomiExtensionConfiguration(
    namespaceIdentifier = "id",
    extName = "Komik Cast",
    pkgNameSuffix = "komikcast",
    extClass = ".KomikCast",
    extVersionCode = 1,
    isNsfw = false,
)
