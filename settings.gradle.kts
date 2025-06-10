pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
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

rootProject.name = "TravelPlanner"
include(":app")
include(":feature:auth")
include(":feature:dashboard")
include(":feature:user")
include(":feature:user:contract")

include(":common:ui")
include(":common:permissions")
include(":common:activityconnector")
include(":common:intents")
include(":common:core")
include(":common:loader")
include(":common:storage")
include(":common:security")

include(":be:backendservice")
include(":be:backendservice:contract")
include(":common:validators")
include(":common:sensor")
