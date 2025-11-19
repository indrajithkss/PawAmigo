// ✅ Root-level build.gradle.kts (PawAmigo/build.gradle.kts)

plugins {
    // Use the same plugin references defined in libs.versions.toml
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Add Firebase Google Services plugin (no version conflict)

    id("com.google.gms.google-services") version "4.4.4" apply false
}
