plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation("com.mysql:mysql-connector-j:8.4.0")
}

tasks.test {
    useJUnitPlatform()
}