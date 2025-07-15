plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" // ✅ Nuevo plugin oficial
}

android {
    namespace = "com.example.drivethrurestaurante"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.drivethrurestaurante"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    // ✅ Ya no necesitas composeOptions con kotlinCompilerExtensionVersion
    // El plugin se encarga automáticamente

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // ✅ Actualiza las versiones de Compose BOM para mejor compatibilidad
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // ✅ Jetpack Compose (usando BOM para versiones consistentes)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // ✅ Material 3
    implementation("androidx.compose.material3:material3")

    // ✅ Navigation con Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // ✅ Activity para Compose
    implementation("androidx.activity:activity-compose:1.8.2")

    // Dependencias comunes
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Módulo compartido
    implementation(project(":shared"))

    // Pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}