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

rootProject.name = "Tickets Search Test"
include(":app")
include(":features-mobile")
include(":core")
include(":features-mobile:palette")
include(":features-mobile:common")
include(":core:network")
include(":features-mobile:main-screen")
include(":core:utils")
include(":features-mobile:hotels-screen")
include(":features-mobile:geo-screen")
include(":features-mobile:subscribe-screen")
include(":features-mobile:profile-screen")
include(":features-mobile:weekend-screen")
include(":features-mobile:hard-screen")
include(":features-mobile:hot-tickets-screen")
include(":features-mobile:country-selected-screen")
include(":features-mobile:tickets-screen")
