plugins {
    alias(libs.plugins.almatar.android.feature)
    alias(libs.plugins.almatar.android.library.compose)
}

android {
    namespace = "com.almatar.feature.home"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))

    androidTestImplementation(libs.hilt.android.testing)
    testImplementation(libs.hilt.android.testing)
}