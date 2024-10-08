pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.7"
  id("com.gradle.develocity") version "3.17.5"
}

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}
develocity {
  buildScan {
    termsOfUseAgree.set("true")
  }
}

gitHooks {
//  preCommit {
//    from {
//      """
//      ./gradlew spotlessApply && git add .
//      """
//    }
//  }
  commitMsg {
    conventionalCommits {
      defaultTypes()
      types("format")
    }
  }
  createHooks(true)
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Viewer"

include("docs")
