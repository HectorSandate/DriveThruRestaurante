/*plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}*/
plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm) // Use the version from libs.versions.toml
    application
}

/*repositories {
    mavenCentral()
}*/

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-websockets:2.3.4")
    implementation("ch.qos.logback:logback-classic:1.4.11") // para ver logs en consola
}

application {
    mainClass.set("Servidor.kt") // nombre del archivo donde pondrás fun main()
}

