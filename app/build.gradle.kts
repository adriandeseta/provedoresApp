plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // ¡Este es el plugin principal de Kotlin!
    id("org.jetbrains.kotlin.plugin.compose") // Plugin para Compose
    id("com.google.dagger.hilt.android") // Plugin de Hilt
    id("kotlin-kapt") // Solo
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.pagoproveedoresapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pagoproveedoresapp"
        minSdk = 24
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler) // KSP para Room
    implementation(libs.androidx.room.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // solo KSP, NO kapt aquí

    implementation(libs.androidx.hilt.navigation.compose)

    // ViewModel + Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)

    // Jetpack Compose (si no los tenés)
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.androidx.navigation.compose)

    // Coroutines (si aún no los tenés)
    implementation(libs.kotlinx.coroutines.android)

    // Eliminé el kapt(libs.hilt.compiler.v2511) porque estás usando ksp para hilt-compiler
}
