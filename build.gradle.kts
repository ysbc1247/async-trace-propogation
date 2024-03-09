import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.0"

    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.0-Alpha"
    id("com.netflix.dgs.codegen") version "5.12.4" apply false

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

java.sourceCompatibility = JavaVersion.VERSION_17


group = "kr.projects"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    dependencyManagement {
        imports {
            mavenBom("de.codecentric:spring-boot-admin-dependencies:3.0.2")
            mavenBom("io.micrometer:micrometer-bom:1.10.5")
            mavenBom("io.micrometer:micrometer-tracing-bom:1.0.3")
        }
    }
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        // Spring Boot starter for building web applications, includes RestController
    }
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-logging") {
        // SLF4J with Logback, default logging starter
    }
    implementation("org.springframework:spring-context-support") {
        // Optional, if you need additional context support, e.g., for @Configuration
    }
    implementation("io.micrometer", "micrometer-observation")
    implementation("io.micrometer", "micrometer-tracing")
    implementation("io.micrometer:micrometer-tracing-bridge-brave:1.2.3")
    implementation("io.micrometer", "context-propagation")
    implementation("io.github.microutils", "kotlin-logging-jvm", "3.0.5")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.security:spring-security-core:6.2.2")

    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
