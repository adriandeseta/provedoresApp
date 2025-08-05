pluginManagement {
    repositories {
        gradlePluginPortal()
        google() // Add google() here to find Android and other Google plugins
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PagoProveedoresApp"
include(":app")