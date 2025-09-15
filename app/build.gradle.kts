val junitVersion = "5.11.0"
val junitApiVersion = "5.11.0"
val picocliVersion = "4.7.6"
val jacksonDatabindVersion = "2.18.2"
val jacksonDataFormatYamlVersion = "2.15.2"
val picocliCodegenVersion = "4.7.6"
val assertjCoreVersion = "3.24.2"
val junitEngineVersion = "5.9.2"
val jetbrainsAnnotationsVersion = "23.0.0"
val slf4jVersion = "2.0.7"
val logbackVersion = "1.5.13"
val lombokVersion = "1.18.38"


plugins {
    id("application")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "6.0.1.5171"
}

group = "hexlet.code"
version = "1.0.3-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitApiVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitApiVersion")
    testImplementation("org.assertj:assertj-core:$assertjCoreVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitEngineVersion}")
    implementation("info.picocli:picocli:$picocliVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonDatabindVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonDataFormatYamlVersion")
    implementation("org.jetbrains:annotations:${jetbrainsAnnotationsVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jVersion}")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    annotationProcessor("info.picocli:picocli-codegen:$picocliCodegenVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
}

testing {
    suites {
        getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}

    sonar {
        properties {
            property("sonar.projectKey", "neutrall85_java-project-71")
            property("sonar.organization", "neutrall85")
            property("sonar.host.url", "https://sonarcloud.io")
        }
    }

    application {
        mainClass = "hexlet.code.App"
    }