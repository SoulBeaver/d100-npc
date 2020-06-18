buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")
    }
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")
plugins {
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Http4k
    implementation("org.http4k:http4k-core:3.251.0")
    implementation("org.http4k:http4k-server-undertow:3.251.0")

    // Random Table rolling
    implementation("dev.christianbroomfield:d100:0.7.0")

    // Logging
    implementation("io.github.microutils:kotlin-logging:1.7.10")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")

    // CLI
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    check {
        dependsOn("ktlintFormat")
    }
}
