import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent


plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.6"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    testImplementation("org.jacoco:org.jacoco.agent:0.8.10")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

checkstyle {
    toolVersion = "8.45"
    configFile = file("${rootProject.projectDir}/config/checkstyle/checkstyle.xml")
}

tasks.named<Checkstyle>("checkstyleMain") {
    source("src/main/java")
    include("**/*.java")
    exclude("**/generated/**")
}

tasks.named<Checkstyle>("checkstyleTest") {
    source("src/test/java")
    include("**/*.java")
    exclude("**/generated/**")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

tasks.register<Exec>("codeClimateCoverage") {
    group = "verification"
    description = "Upload coverage to CodeClimate"
    commandLine = listOf(
        "./cc-test-reporter", "after-build", "--exit-code", "0"
    )
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21) // switched to an older version of java for jacoco to work
    }
}

