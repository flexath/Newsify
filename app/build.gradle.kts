plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.flexath.newsify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.flexath.newsify"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // ViewModel and LiveData for MVVM architecture
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-rc01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Dagger Hilt
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-common:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Navigation Component
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Splash Api
    implementation("androidx.core:core-splashscreen:1.0.1")

    // To Change Status Bar Color
    val uiControllerVersion = "0.32.0"
    implementation("com.google.accompanist:accompanist-systemuicontroller:$uiControllerVersion")

    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.5.4")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    //Paging 3
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
}