plugins {
  alias(libs.plugins.asciidoctorPdf)
  alias(libs.plugins.asciidoctorJvm)
  alias(libs.plugins.asciidoctorEpub)
  alias(libs.plugins.asciidoctorEditconfig)
  alias(libs.plugins.asciidoctorGem)
  idea
}

repositories {
  mavenLocal()
  mavenCentral()
  ruby {
    gems()
  }
}

asciidoctorj {
  modules {
    diagram.use()
    pdf.use()
    epub.use()
  }
}

tasks.asciidoctor {
  parallelMode = true
  jvm {
    jvmArgs(
      "--add-opens",
      "java.base/sun.nio.ch=ALL-UNNAMED",
      "--add-opens",
      "java.base/java.io=ALL-UNNAMED",
    )
  }
}

tasks.build {
  dependsOn(tasks.asciidoctor)
}

idea {
  module {
    sourceDirs = project.tasks.asciidoctor.get().sourceFileTree.toMutableSet()
  }
}