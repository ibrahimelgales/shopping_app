
plugins {
    alias(libs.plugins.almatar.android.feature)
    alias(libs.plugins.almatar.android.library.compose)
}

android {
    namespace = "com.almatar.feature.show_product"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}