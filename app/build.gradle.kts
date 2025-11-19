plugins {
    

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")                  // KAPT for Glide, Room, etc.
    id("com.google.gms.google-services") // Firebase Google services
}

android {
    namespace = "com.app.pawamigo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.pawamigo"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfigs {
            create("release") {
                storeFile = file("C:/Users/indra/Desktop/ANDROIDAPP/my-release-key.jks")
                storePassword = "kanikunnel"
                keyAlias = "releasekey"
                keyPassword = "kanikunnel"
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                signingConfig = signingConfigs.getByName("release")
            }
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug { }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    // ✅ UPDATED — fixed deprecated packagingOptions
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Firebase BOM - keeps firebase libs versions aligned
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))

    // Firebase libraries (use ktx variants)
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    implementation("de.hdodenhof:circleimageview:3.1.0")


    // AndroidX & Material
    implementation("androidx.core:core-ktx:1.11.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // Glide (image loading) + kapt
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    // Coroutines for Firebase tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // Optional utilities
    implementation("com.google.guava:guava:32.1.2-android")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.6")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
