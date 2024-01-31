plugins {
    alias(libs.plugins.almatar.android.library)
    alias(libs.plugins.almatar.android.library.compose)
    alias(libs.plugins.almatar.android.hilt)
}

android {
    namespace = "com.almatar.core.testing"
}

dependencies {
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(project(":core:data"))
    api(project(":core:common"))
    api(project(":core:design-system"))

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.testharness)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
}