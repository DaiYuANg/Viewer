import com.xenoterracide.gradle.semver.GitMetadataExtension
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
  java
  application
  idea
  alias(libs.plugins.quarkus)
  alias(libs.plugins.lombok)
  alias(libs.plugins.gradleDotenv)
  alias(libs.plugins.spotless)
  alias(libs.plugins.frontend)
  alias(libs.plugins.gitVersion)
  alias(libs.plugins.plantuml)
  alias(libs.plugins.taskTree)
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
  implementation(libs.apacheCommonIO)
  implementation(libs.jacksonGuava)
  implementation(libs.quarkusPrettytime)
  implementation(libs.quarkusKubernetes)
  implementation(libs.jacksonDataTypeHibernate6)
  implementation(libs.jacksonDataTypeEclipseCollections)
  implementation(libs.quarkusBucket4j)
  implementation(libs.quarkusMinio)
  implementation(libs.quarkusMinioNative)

  compileOnly(libs.autoFactory)
  annotationProcessor(libs.autoFactory)
  annotationProcessor(libs.recordBuilderProcessor)
  implementation(libs.mapstruct)
  implementation(libs.log4jApi)
  implementation(libs.log4jApiCore)
  implementation(libs.log4jToSlf4j)
  implementation(libs.quarkusQute)
  implementation(libs.quarkusInfinispan)
  implementation(libs.quarkusContainer)
  implementation(libs.quarkusKafka)
  implementation(libs.vertxIOUring)
  annotationProcessor(libs.mapstructProcessor)
  annotationProcessor(libs.hibernatejpaModelGen)
  annotationProcessor(libs.infinispanProtostreamProcessor)
  testImplementation(libs.quarkusQuinoaTest)
  testImplementation(libs.quarkusJunit5)
  testImplementation(enforcedPlatform(libs.junitBom))
  testImplementation(libs.restAssured)
  testImplementation(libs.mockitoCore)
  testImplementation(libs.mockitoJunit)
  testImplementation(libs.dataFaker)
  testImplementation(libs.junitApi)
  testImplementation(libs.vertxJunit)
  testImplementation(libs.quarkusJacoco)
  testImplementation(libs.quarkusVirtualThreadTest)
  testImplementation(libs.quarkusJunit5Internal)
  testImplementation(libs.quarkusTestSecurityJwt)
}

val gitVersion: GitMetadataExtension = semver.git
group = "org.guarder"
version = gitVersion.commitShort!!

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

val jdkVersion: String = libs.versions.jdkVersion.get()

idea {
  project {
    jdkName = jdkVersion
    languageLevel = IdeaLanguageLevel(jdkVersion)
    vcs = "Git"
  }
}

tasks.processResources { duplicatesStrategy = DuplicatesStrategy.INCLUDE }