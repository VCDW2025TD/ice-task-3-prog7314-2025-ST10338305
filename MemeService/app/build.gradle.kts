plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.memeservice"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.memeservice"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("androidx.fragment:fragment-ktx:1.6.2")

// Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

// Lifecycle ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

// RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

// Coil for GIF loading
    implementation("io.coil-kt:coil-gif:2.5.0")

    implementation("io.coil-kt:coil:2.5.0")



}