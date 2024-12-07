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
    implementation("org.apache.commons:commons-lang3:3.12.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.jacoco:org.jacoco.agent:0.8.10")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))

//    testImplementation("org.assertj:assertj-core:3.23.1")
//    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
//    annotationProcessor("info.picocli:picocli-codegen:4.6.3")
//    testImplementation("org.slf4j:slf4j-log4j12:2.0.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

tasks.test {
    testLogging {
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

tasks.register<Exec>("codeClimateCoverage") {
    group = "verification"
    description = "Upload coverage to CodeClimate"
    commandLine = listOf(
        "./cc-test-reporter", "after-build", "--exit-code", "0"
    )
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Убедитесь, что Java 21 доступна
    }
}

