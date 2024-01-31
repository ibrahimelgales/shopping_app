pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shopping APP"
include(":app")
include(":core:design-system")
include(":core:database")
include(":core:data")
include(":core:common")
include(":core:domain")
include(":feature:home")
include(":feature:upsert")
include(":core:testing")
