
plugins {
    alias(libs.plugins.almatar.android.library)
    alias(libs.plugins.almatar.android.hilt)
    alias(libs.plugins.almatar.android.room)
}

android {
    namespace = "com.almatar.core.database"
    defaultConfig {
        testInstrumentationRunner = "com.almatar.core.testing.AlmatarTestRunner"
    }
}

dependencies {
    androidTestImplementation(project(":core:testing"))
}