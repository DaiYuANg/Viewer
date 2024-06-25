plugins {
  java
  application
  alias(libs.plugins.quarkus)
  alias(libs.plugins.lombok)
  alias(libs.plugins.gradleDotenv)
  alias(libs.plugins.spotless)
  alias(libs.plugins.frontend)
}

repositories {
  mavenLocal()
  mavenCentral()
  google()
  gradlePluginPortal()
}

dependencies {
  implementation(enforcedPlatform(libs.quarkusPlatform))
  implementation(libs.quarkusHibernateReactive)
  implementation(libs.quarkusRest)
  implementation(libs.quarkusConfigYaml)
  implementation(libs.vertxHazelcast)
  implementation(libs.quarkusJwt)
  implementation(libs.quarkusJwtBuild)
  implementation(libs.quarkusVertx)
  implementation(libs.quarkusInfo)
  implementation(libs.quarkusHibernateReactivePanache)
  implementation(libs.quarkusHibernateValidator)
  implementation(libs.quarkusHibernateEnvers)
  implementation(libs.quarkusRestJackson)
  implementation(libs.quarkusReactivePgClient)
  implementation(libs.quarkusCache)
  implementation(libs.quarkusArc)
  implementation(libs.quarkusQuinoa)
  implementation(libs.quarkusOpenAPI)
  implementation(libs.quarkusHibernateTypes)
  implementation(libs.quarkusQuartz)
  implementation(libs.quarkusScheduler)
  implementation(libs.quarkusPOI)
  implementation(libs.quarkusMailer)
  implementation(libs.quarkusLoggingManager)
  implementation(libs.guava)
  implementation(libs.eclipseCollections)
  implementation(libs.eclipseCollectionsAPI)
  implementation(libs.recordBuilderCore)
  implementation(libs.hibernateSpatil)
  implementation(libs.useragent)
  implementation(libs.vavr)
  implementation(libs.apacheCommonLang3)
  implementation(libs.semver4j)
  implementation(libs.slf4j)
  implementation(libs.oshi)
  testImplementation(libs.mockitoCore)
  testImplementation(libs.mockitoJunit)
  annotationProcessor(libs.recordBuilderProcessor)
  implementation(libs.mapstruct)
  implementation(libs.log4jApi)
  implementation(libs.log4jApiCore)
  implementation(libs.log4jToSlf4j)
  annotationProcessor(libs.mapstructProcessor)
  annotationProcessor(libs.hibernatejpaModelGen)
  testImplementation(libs.quarkusQuinoaTest)
  testImplementation(libs.quarkusJunit5)
  testImplementation(enforcedPlatform(libs.junitBom))
}

group = "org.guarder"
version = "1.0-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
  systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
  options.compilerArgs.add("-parameters")
}

quarkus {
  buildForkOptions {
    jvmArgs("-XX:ReservedCodeCacheSize=2G")
  }
}

frontend {
  nodeVersion.set("20.9.0")
}